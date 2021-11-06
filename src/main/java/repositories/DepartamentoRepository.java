package repositories;

import database.DatabaseController;
import model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
