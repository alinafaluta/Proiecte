from Domain.Contract import Contract,ContractValidator


class ContractFileRepo:
    """
    stores/ retrieves contracts from file
    """

    def __init__(self,fileName):
        self.__file = fileName

    def __loadFromFile(self):
        """
            loads contracts from file
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
            contract = Contract(attrs[0], attrs[1], attrs[2], attrs[3])
            rez.append(contract)
            line = f.readline().strip()
        f.close()
        return rez

    def storeContracts(self, contract):
        """
        stores movies to the file
        :return: validate error if exists
        """
        validator = ContractValidator()
        allContracts = self.__loadFromFile()

        if validator.validate(contract) == True:
            for i in allContracts:
                if i.getIdContract() == contract.getIdContract():
                    raise ValueError("Id already exists")
                elif i == contract:
                    raise ValueError("Contract already exists")
            allContracts.append(contract)
            self.__storeToFile(allContracts)
        else:
            return validator.validate(contract)

    def removeMovie(self, id):

        allContracts = self.__loadFromFile()
        poz = -1
        for i in range (len(allContracts)):
            if allContracts[i].getIdContract() == id:
                poz = i

        if poz == -1:
            raise ValueError("No movie with this title")
        contract = allContracts[poz]
        del allContracts[poz]
        self.__storeToFile(allContracts)
        return contract

    def __storeToFile(self, list):
        """
            Store all the clients  in to the file
            raise CorruptedFileException if we can not store to the file
        """

        with open(self.__file, "w") as f:
            for contract in list:
                strf = str(contract.getIdContract()) + ',' + str(contract.getIDMovie()) + ',' + contract.getTitleMovie() + ',' + contract.getNINclient()
                strf = strf + '\n'
                f.write(strf)

    def removeAll(self):
        """
          Remove all the students from the repository
          post: the repository is empty
        """
        self.__storeToFile([])

    def get_all(self):
        return self.__loadFromFile()

    def size(self):
        return len(self.__loadFromFile())

