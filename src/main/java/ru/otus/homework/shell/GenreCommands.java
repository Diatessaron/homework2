package ru.otus.homework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.GenreService;

@ShellComponent
public class GenreCommands {
    private final GenreService genreService;

    public GenreCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(key = {"gi", "gInsert"}, value = "Insert genre. Arguments: genreName. " +
            "Please, put comma instead of space in each argument or simply put the arguments in quotes.")
    public String insert(@ShellOption("Name") String name) {
        return genreService.saveGenre(reformatString(name));
    }

    @ShellMethod(key = {"gbn", "genreByName", "gByName"}, value = "Get genre by name. Arguments: genreName. " +
            "Please, put comma instead of space in each argument or simply put the arguments in quotes.")
    public String getGenreByName(@ShellOption("Name") String name) {
        return genreService.getGenreByName(reformatString(name)).toString();
    }

    @ShellMethod(key = {"gga", "gGetAll"}, value = "Get all genres")
    public String getAll() {
        return genreService.getAll().toString();
    }

    @ShellMethod(key = {"gu", "gUpdate"}, value = "Update genre in repository. Arguments: oldGenreName, newGenreName. " +
            "Please, put comma instead of space in each argument or simply put the arguments in quotes.")
    public String update(@ShellOption("OldGenreName") String oldGenreName,
                         @ShellOption("Name") String name) {
        return genreService.updateGenre(reformatString(oldGenreName), reformatString(name));
    }

    @ShellMethod(key = {"gd", "gDelete"}, value = "Delete genre by name. Arguments: genreName. " +
            "Please, put comma instead of space in each argument or simply put the arguments in quotes.")
    public String deleteByName(@ShellOption("Name") String name) {
        return genreService.deleteGenreByName(reformatString(name));
    }

    private String reformatString(String str) {
        return String.join(" ", str.split(","));
    }
}
