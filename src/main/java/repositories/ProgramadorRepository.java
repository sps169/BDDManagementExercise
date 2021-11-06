package repositories;

import database.DatabaseController;
import model.Lenguajes;
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

    private static List<Lenguajes> procesarStringLenguajes (String lenguajes) {
        return Arrays.stream(lenguajes.split(";")).map(Lenguajes::valueOf).collect(Collectors.toList());
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
}
