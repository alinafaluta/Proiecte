from Domain.Clients import Client,ClientValidator


class ClientFileRepo:
    """
    store/retrieve clients from file
    """

    def __init__(self, fileN):
        """
          initialise repository
          fileName - string, file path where the clients are stored
          post: clients are loaded from the file
        """
        self.__fName = fileN

    def __loadFromFile(self):
        """
          Load clients from file
          raise CorruptedFileException if there is an error when reading from the file
        """
        try:
            f = open(self.__fName, "r")
        except IOError:
            # file not exist
            return
        line = f.readline().strip()
        rez = []
        while line != "":
            attrs = line.split(",")
            client = Client(attrs[0], attrs[1], attrs[2], attrs[3])
            rez.append(client)
            line = f.readline().strip()
        f.close()
        return rez

    def storeClient(self, client):
        """
          Store the client to the file.Overwrite store
          Post: client is stored to the file
        """
        validator = ClientValidator()
        allClients = self.__loadFromFile()
        if validator.validate(client) == True:
            for i in allClients:
                if i == client:
                    raise ValueError("Client already exists")
                elif i.getId() == client.getId():
                    raise ValueError("dublicated id")
                elif i.getNIN()== client.getNIN():
                    raise ValueError("Client already exists")
            allClients.append(client)
            self.__storeToFile(allClients)
        else :
            return validator.validate(client)


    def removeClient(self, CNP):

        allClients = self.__loadFromFile()
        poz = -1
        for i in range(len(allClients)):
            if allClients[i].getNIN()==CNP:
                poz = i
        if poz == -1:
            raise ValueError("No client for the NIN:"+CNP)
        client = allClients[poz]
        del allClients[poz]
        self.__storeToFile(allClients)
        return client

    def __storeToFile(self, sts):
        """
         Store all the clients  in to the file
         raise CorruptedFileException if we can not store to the file
        """
        with open(self.__fName, "w") as f:
            for st in sts:
                strf = str(st.getId()) + "," + st.getName() + ","+ st.getSurname() + ","+ st.getNIN()
                strf = strf + "\n"
                f.write(strf)

    def find(self, id):
        """
          Find the student for a given id
          id - string
          return student with the given id or None if there is no student with the given id
        """
        clients = self.__loadFromFile()
        for i in clients:
            if i.getId() == id:
                return i
        return None

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

