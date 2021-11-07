package repositories;

import database.DatabaseController;
import model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartamentoRepository {
    public static DatabaseController db = DatabaseController.getInstance();

    public static Optional<Departamento> selectDepartamentoConId(int id) {
        String query = "select * from departamentos where id_departamento = ?";
        Optional<Departamento> departamentoOptional = Optional.empty();
        try {
            db.open();
            ResultSet dbResult = db.select(query, id).orElseThrow(() -> new SQLException("Error at select departamento with id: " + id)) ;
            if (dbResult.next()) {
                departamentoOptional = getDepartamentoFromResultSet(dbResult);
            }
            db.close();
        }catch (SQLException e) {
            System.err.println("Error at select departamento with id: " + id);
        }
        return departamentoOptional;
    }

    private static Optional<Departamento> getDepartamentoFromResultSet(ResultSet dbResult) throws SQLException {
        return Optional.of(
                new Departamento(
                        dbResult.getLong("id_departamento"),
                        dbResult.getString("nombre_departamento"),
                        dbResult.getLong("presupuesto"),
                        ProgramadorRepository.selectProgramadorById(dbResult.getInt("id_departamento")).orElse(null),
                        ProgramadorRepository.selectProgramadoresDeDepartamentoConId(dbResult.getInt("id_departamento"))
                ));
    }

    public static List<Departamento> getAllDepartamentos(){
        String query = "select * from departamentos";
        List<Departamento> departamentos = new ArrayList<>();
        try {
            db.open();
            ResultSet dbResult = db.select(query).orElseThrow(() -> new SQLException("Error at select departamentos")) ;
            while (dbResult.next()) {
                getDepartamentoFromResultSet(dbResult).ifPresent(departamentos::add);
            }
            db.close();
        }catch (SQLException e) {
            System.err.println("Error at select departamentos");
        }
        return departamentos;
    }

    public static Optional<Departamento> deleteDepartamento (Departamento departamento) {
        String query = "delete from departamentos where id_departamento = ?";
        Optional<Departamento> returnOptional = Optional.empty();
        try {
            db.open();
            if (db.delete(query, departamento.getId()) > 0) {
                departamento.getProgramadores().forEach(ProgramadorRepository::deleteProgramador);
                returnOptional = Optional.of(departamento);
            }
            db.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return returnOptional;
    }

    public static Optional<Departamento> insertDepartamento (Departamento departamento) {
        String query = "insert into departamentos values (?, ?, ?, ?)";
        Optional<Departamento> returnOptional = Optional.empty();
        try {
            db.open();
            ResultSet result = db.insert(query,
                    departamento.getId(),
                    departamento.getNombre(),
                    departamento.getPresupuesto(),
                    departamento.getJefe().getId()
            ).orElseThrow(() -> new SQLException());
            returnOptional = Optional.of(departamento);
            if (result.next()) {
                departamento.getProgramadores().forEach(ProgramadorRepository::insertProgramador);
            }
            db.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return returnOptional;
    }

    public static Optional<Departamento> updateDepartamento (Departamento departamento) {
        String query = "update departamentos set nombre_departamento = ?, presupuesto = ?, id_jefe = ? where id_departamento = ?";
        Optional<Departamento> returnOptional = Optional.empty();
        try {
            db.open();
            if (db.update(query, departamento.getNombre(), departamento.getPresupuesto(), departamento.getJefe().getId(), departamento.getId()) > 0) {
                returnOptional = Optional.of(departamento);
            }
            db.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return returnOptional;
    }
}
