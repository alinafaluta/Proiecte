from copy import deepcopy


class InMemoryRepository:

    def __init__(self):
        self.movies = []
        self.__undo = []

    def storeMovie(self, movie):
        """
         Stores the movie list.
         numberOf is the number of copies of a movie.
         raises ValueError if the movie already exists
         """
        if self.movies != []:
            self.__undo.append(deepcopy(self.movies))
        for i in self.movies:
            if i.getId() == movie.getId():
                raise ValueError
        self.movies.append(movie)
        return movie

    def removeMovie(self, title):
        """
        Deletes a movie by title.
        """
        Remove = []
        for i in self.movies:
            if i.getTitle() != title:
                Remove.append(i)
        if len(Remove) == self.size():
            raise ValueError('No movie with this title')
        else:
            self.__undo.append(deepcopy(self.movies))
            self.setMovies(Remove)

    def setMovies(self, list):
        self.movies = list

    def size(self):
        """
         Returns how many different movies are in the repository.
        """
        return len(self.movies)

    def getAll(self):
        return self.movies

    def __len__(self):
        return len(self.movies)

    def undo(self):
        if len(self.__undo) > 1:
            self.movies, self.__undo[-1] = self.__undo[-1], self.movies
            del self.__undo[-1]
        elif len(self.__undo) == 1:
            self.movies, self.__undo[0] = self.__undo[0], self.movies
            del self.__undo[0]
        elif len(self.__undo) == 0:
            lista_vida = []
            self.movies, lista_vida = lista_vida, self.movies

