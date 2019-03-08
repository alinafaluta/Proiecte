class Contract():
    """
        In this class we are going to combine the id from the movies with the NIN of the client to create a renting Contract
    """
    def __init__(self, idContract, idMovie, title, NIN):
        self.__idMovie = idMovie
        self.__ninClient = NIN
        self.__idContract = idContract
        self.__titleMovie = title

    def getIDMovie(self):
        """
            :return the id of the current client as an int value
        """
        return self.__idMovie

    def getTitleMovie(self):
        """
            :return the tile of the current movie that the client wants to rent
        """
        return self.__titleMovie

    def getIdContract(self):
        """
            :return the id of the current contract as an int value
        """
        return self.__idContract

    def getNINclient(self):
        """
            :return the NIN (National Identification Number) of the current client that is included in the contract as a string
        """
        return self.__ninClient

    def setId(self, id):
        """
            gives the curent contract a new id
        """
        self.__idMovie = id

    def setNIN(self, nin):
        """
            gives the curent contract a new NIN(National Identification Number)
        """
        self.__ninClient = nin

    def __str__(self):
        return "ID Contract: " + str(self.__idContract) + " Title: " + str(self.__titleMovie) + " NIN: " + str(self.__ninClient)


class ContractValidator():
    """
        validates the nin and id form the contract
    """
    def validate(self,contract):
        errors = ""
        if contract.getTitleMovie() == "":
            errors += "Movie title can not be empty"
        if contract.getIDMovie() == "":
            errors += "Id can not be empty;"
        if contract.getNINclient() == "":
            errors += "NIN can not be empty"
        if len(contract.getNINclient()) < 13:
            errors += "NIN can not have less than 13 digits"
        if len(contract.getNINclient()) > 13:
            errors += "NIN can not have more than 13 digits"
        if type(contract.getNINclient()) == float:
            errors += "NIN can not be float"
        if not contract.getNINclient().isdigit():
            errors += "NIN has to have 13 digits"
        if len(errors) > 0:
            raise ValueError(errors)
        return True


