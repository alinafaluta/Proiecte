from Domain.Movies import movieValidator,Movie
from Repository.MovieRepository import InMemoryRepository
import random
import string

class MovieService:
    """
        controls the movie list
    """

    def __init__(self, repository):
        self.repository = repository
        self.__index = 0

    def createMovie(self,  title, type, description, year):
        """
            add a movie in the list
            returns the movie
        """
        if self.repository.getAll() != []:
            list = self.repository.getAll()
            id_list = int(list[-1].getId())
            self.__index = id_list+1
        else:
            self.__index = self.__index + 1
        id = self.__index
        movie = Movie(id, title, type, description, year)
        validator = movieValidator()
        if validator.validate(movie):
            self.repository.storeMovie(movie)
            return True
        else:
            if validator.validate(movie) not in None:
                self.__index = self.__index - 1
                return validator.validate(movie)

    def deleteMovie(self, title):
        """
            Deletes a movie by id
        """
        self.repository.removeMovie(title)
        return self.repository

    def showMovieList(self):
        """
            Returns the full movie list.
        """
        lista = self.repository.getAll()
        if lista != []:
            return lista
        else:
            return False

    def updateMovie(self, title, newTitle, newType, newDescription, newYear):
        allMovies = self.repository.getAll()
        m = []
        for movie in allMovies:
            if movie.getTitle() == title:
                movie.setTitle(newTitle)
                movie.setDescription(newDescription)
                movie.setYear(newYear)
                movie.setType(newType)
                m.append(movie)
        if len(m) > 0:
            return m

    def searchMovie(self, title):
        """
        searches by title in the movie list
        returns a list with movie instances with the matching name or returns None
        """
        m = []
        allMovies = self.repository.getAll()
        for i in allMovies:
            if i.getTitle() == title:
                m.append(i)
        if len(m) != 0:
            return m
        else:
            return None

    def searchMovie_id(self, id):
        """
        searches a movie by id in the movie list
        returns the title of the movie
        """
        allMovies = self.repository.getAll()
        for i in allMovies:
            if i.getId() == id:
                return i.getTitle()

    def undoMovieList(self):
        """
            undoes the movie list once
        """
        self.repository.undo()

    def RandomMovie(self):
        Movie_List = []
        ok = 0
        while True:
            random_id = "".join(random.choices(string.digits, k = 3))
            random_title = "".join(random.choices(string.ascii_letters, k = 7))
            random_descriere = "".join(random.choices(string.ascii_letters, k=15))
            random_type = "".join(random.choices(string.ascii_letters, k=5))
            random_year= "".join(random.choices(string.digits, k = 4))
            if random_year[0] == 0:
                ok = 0

            movie = Movie(random_id, random_title, random_type, random_descriere, random_year)
            if movie in Movie_List:
                    ok = 0
            else:
                Movie_List.append(movie)
                return movie
            if ok == 0:
                break

    def getALL(self):
        return self.repository.getAll()