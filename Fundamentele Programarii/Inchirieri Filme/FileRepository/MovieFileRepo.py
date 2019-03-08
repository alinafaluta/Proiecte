from Domain.Movies import Movie, movieValidator


class MovieFileRepo:
    """
    stores/ retrieves movies from file
    """

    def __init__(self, fileName):
        self.__file = fileName

    def __loadFromFile(self):
        """
            loads movies from file
            :raise CorruptedFileException if there is an error when reading from the file
        """
        try:
            f = open(self.__file, "r")
        except IOError:
            return

        line = f.readline().strip()
        rez = []
        while line != "":
            attrs = line.split(',')
            movie = Movie(attrs[0], attrs[1], attrs[2], attrs[3], attrs[4])
            rez.append(movie)
            line = f.readline().strip()
        f.close()
        return rez

    def storeMovie(self, movie):
        """
        stores movies to the file
        :return: validate error if exists
        """
        validator = movieValidator()
        allMovies = self.__loadFromFile()

        if validator.validate(movie) == True:
            for i in allMovies:
                if i.getId() == movie.getId():
                    raise ValueError("Id already exists")
                elif i == movie:
                    raise ValueError("Movie already exists")
            allMovies.append(movie)
            self.__storeToFile(allMovies)
        else:
            return validator.validate(movie)

    def removeMovie(self, title):

        allMovies = self.__loadFromFile()
        poz = -1
        for i in range (len(allMovies)):
            if allMovies[i].getTitle() == title:
                poz = i

        if poz == -1:
            raise ValueError("No movie with this title")
        movie = allMovies[poz]
        del allMovies[poz]
        self.__storeToFile(allMovies)
        return movie

    def __storeToFile(self, list):
        """
            Store all the clients  in to the file
            raise CorruptedFileException if we can not store to the file
        """

        with open(self.__file, "w") as f:
            for movie in list:
                strf = str(movie.getId()) + ',' + movie.getTitle() + ',' + movie.getType() + ',' + movie.getDescription() + ','+ str(movie.getYear())
                strf = strf + '\n'
                f.write(strf)

    def removeAll(self):
        """
          Remove all the students from the repository
          post: the repository is empty
        """
        self.__storeToFile([])

    def getAll(self):
        return self.__loadFromFile()

    def size(self):
        return len(self.__loadFromFile())


