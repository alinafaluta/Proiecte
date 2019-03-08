class Movie:

    def __init__(self, id, title, type, description, year):
        self.__id = id
        self.__title = title
        self.__description = description
        self.__type = type
        self.__year = year

    def getId(self):
        """
            :return the id of the current movie as an int value
        """
        return self.__id

    def getTitle(self):
        """
            :return the title of the current movie as a string
        """
        return self.__title

    def getDescription(self):
        """
            :return the description of the current movie as a string
        """
        return self.__description

    def getType(self):
        """
            :return the type of the current movie as a string
        """
        return self.__type

    def getYear(self):
        """
            :return the year of the apparance of the current movie as a int value
        """
        return self.__year

    def __str__(self):
        return "Id:"+str(self.__id)+" Title:"+str(self.__title)+" Type: "+str(self.__type)+" Description: "+str(self.__description)+" Year:"+str(self.__year)

    def setTitle(self,newTitle):
        self.__title = newTitle

    def setType(self,newType):
        self.__type = newType

    def setDescription(self,newDescription):
        self.__description = newDescription

    def setYear(self,newYear):
        self.__year = newYear


class movieValidator:
    """
        Class responsible with validation
    """

    def validate(self, movie):
        """
            Validate a movie
            movie - movie
            raise ValueError
            if: Id, title, description, type, or year are empty
            if: year < 0
        """

        errors = ""
        if movie.getId() == "":
            errors += "Id can not be empty;"
        if movie.getTitle() == "":
            errors += "Title can not be empty;"
        if movie.getType() == "":
            errors += "Type can not be empty"
        if movie.getYear() == "":
            errors += "Year can not be empty"
        if int(movie.getYear()) < 0 :
            errors += "Year can not be negative"
        if type(movie.getYear()) == float:
            errors += "Year can not be float"
        if len(errors) > 0:
            raise ValueError(errors)
        return True
