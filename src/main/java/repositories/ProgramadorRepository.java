package repositories;

import database.DatabaseController;
import model.Lenguaje;
import model.Programador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProgramadorRepository {
    public static DatabaseController db = DatabaseController.getInstance();

    public static Optional<Programador> selectProgramadorById(int idProgramador) {
        String query = "select * from programadores where id_programador = ?";
        Optional<Programador> programadorOptional = Optional.empty();
        try {
            db.open();
            ResultSet dbResult = db.select(query, idProgramador).orElseThrow(() -> new SQLException("Error at select programador with id: " + idProgramador)) ;
            if (dbResult.next()) {
                programadorOptional = getProgramadorFromResultSet(dbResult);
            }
            db.close();
        }catch (SQLException e) {
            System.err.println("Error at select programador with id: " + idProgramador);
        }
        return programadorOptional;
    }

    private static Optional<Programador> getProgramadorFromResultSet(ResultSet dbResult) throws SQLException {
        return Optional.of(
                new Programador(
                        dbResult.getLong("id_programador"),
                        dbResult.getString("nombre_programador"),
                        dbResult.getInt("experiencia"),
                        dbResult.getLong("salario"),
                        dbResult.getInt("id_departamento"),
                        procesarStringLenguajes(dbResult.getString("Lenguajes"))
                ));
    }

    private static List<Lenguaje.Lenguajes> procesarStringLenguajes (String lenguajes) {
        return Arrays.stream(lenguajes.split(";")).map(Lenguaje.Lenguajes::valueOf).collect(Collectors.toList());
    }

    public static List<Programador> selectAllProgramadores() {
        String query = "select * from programadores";
        List<Programador> programadores = new ArrayList<>();
        try {
            db.open();
            ResultSet dbResult = db.select(query).orElseThrow(() -> new SQLException("Error at select programadores")) ;
            while (dbResult.next()) {
                Optional<Programador> programador = getProgramadorFromResultSet(dbResult);
                programador.ifPresent(programadores::add);
            }
            db.close();
        }catch (SQLException e) {
            System.err.println("Error at select programadores");
        }
        return programadores;
    }

    public static List<Programador> selectProgramadoresDeDepartamentoConId (int id) {
        String query = "select * from programadores where id_departamento = ?";
        List<Programador> programadores = new ArrayList<>();
        try {
            db.open();
            ResultSet dbResult = db.select(query, id).orElseThrow(() -> new SQLException("Error at select programadores with id departamento: " + id)) ;
            while (dbResult.next()) {
                getProgramadorFromResultSet(dbResult).ifPresent(programadores::add);
            }
            db.close();
        }catch (SQLException e) {
            System.err.println("Error at select programador with id: " + id);
        }
        return programadores;
    }

    public static Optional<Programador> deleteProgramador (Programador programador) {
        String query = "delete from programadores where id_programador = ?";
        Optional<Programador> returnOptional = Optional.empty();
        try {
            db.open();
            if (db.delete(query, programador.getId()) > 0) {
                returnOptional = Optional.of(programador);
            }
            db.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return returnOptional;
    }

    public static Optional<Programador> updateProgramador (Programador programador) {
        String query = "update programadores set nombre_programador = ?, experiencia = ?, salario = ?, id_departamento = ?, lenguajes = ? where id_programador = ?";
        Optional<Programador> returnOptional = Optional.empty();
        try {
            db.open();
            if (db.update(query,
                    programador.getNombre(),
                    programador.getExperiencia(),
                    programador.getSalario(),
                    programador.getId_departamento(),
                    Lenguaje.lenguajesACadena(programador.getLenguajes()),
                    programador.getId())
             > 0) {
                returnOptional = Optional.of(programador);
            }
            db.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return returnOptional;
    }

    public static Optional<Programador> insertProgramador (Programador programador) {
        String query = "insert into programadores values (?, ?, ?, ?, ?, ?)";
        Optional<Programador> returnOptional = Optional.empty();
        try {
            db.open();
            ResultSet result = db.insert(query,programador.getId(), programador.getNombre(), programador.getExperiencia(), programador.getSalario(), programador.getId_departamento(), Lenguaje.lenguajesACadena(programador.getLenguajes())).orElseThrow(() -> new SQLException());
            if (result.next()) {
                programador.setId(result.getLong("id_programador"));
                returnOptional = Optional.of(programador);
            }
            db.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return returnOptional;
    }
}
