from Domain.Clients import Client, ClientValidator
import random
import string


class ClientService:
    def __init__(self, repository):
        self.repository = repository
        self.__index = 0

    def createClient(self,  name, surname, nin):
        """
        add the details of a new client
        """
        if self.repository.getAll() != []:
            list = self.repository.getAll()
            id_list = int(list[-1].getId())
            self.__index = id_list+1
        else:
            self.__index = self.__index + 1
        id_client = self.__index
        customer = Client(id_client, name, surname, nin)
        validator = ClientValidator()
        if validator.validate(customer) is True:
            if len(self.repository.getAll()) >= 1:
                for i in self.repository.getAll():
                    if i.getNIN() == nin:
                        if i.getName() != name and i.getSurname != surname:
                            return "NIN must be unique."
                        else:
                            self.repository.storeClient(customer)
                            return True

                    else:
                        self.repository.storeClient(customer)
                        return True
            else:
                self.repository.storeClient(customer)
                return True
        else:
            self.__index = self.__index - 1
            return validator.validate(customer)

    def searchClient(self, name, surname):
        """
        searches for a client in the list after the name and surname
        :param name: the name of the client
        :param surname: the surname of the client
        :return: a list of the clients that were searched
        """
        list = []
        allClients = self.repository.getAll()
        for client in allClients:
            if client.getName() == name and client.getSurname() == surname:
                list.append(client)
        if len(list) != 0:
            return list
        else:
            return None

    # TODO: Search Recursiv
    def searchRec(self,list, name, surname):
        if len(list)!=0:
            if list[0].getName()==name and list[0].getSurname():
                return list[0]
        else:
            return None
        return self.searchRec(list[1:], name, surname)

    def searchClientRecursiv(self, name, surname):
        list = self.repository.getAll()
        return self.searchRec(list, name, surname)

    def searchClientNin(self, nin):
        """
        searches for a client in the list after the nin
        :param nin = national identification number of the client
        :return: the client that was searched
        """
        m = 0
        allClients = self.repository.getAll()
        for client in allClients:
            if client.getNIN() == nin:
                m = client
        if m != 0:
            return m
        else:
            return None

    def updateClient(self, name, surname, newName, newSurname, newNIN):
        """
        modifies the dates of a clients
        :param name: the initial name of the client
        :param surname: the initial surname of the client
        :param newName: the new name
        :param newSurname: the new surname
        :param newNIN: the new NIN
        :return: the new client
        """
        allClients = self.repository.getAll()
        m = []
        for client in allClients:
            if client.getName() == name and client.getSurname() == surname:
                client.setNIN(newNIN)
                client.setSurname(newSurname)
                client.setName(newName)
                m.append(client)
        if len(m) > 0:
            return m

    def deleteClient(self, NIN):
        """
        deletes a client after his nin
        :param NIN: national identify number
        :return: the modified list
        """
        self.repository.removeClient(NIN)
        return self.repository

    def showClientList(self):
        """
        :return: the list of clients
        """
        if self.repository.getAll() != []:
            return self.repository.getAll()
        else:
            return False

    def undoClientList(self):
        self.repository.undo()

    def returnClient(self, nin):
        """
            searches for a client in the list after the NIN
            :return: the client that was searched
        """
        client = []
        allClients = self.repository.getAll()
        for i in allClients:
            if i.getNIN == nin:
                client.append(i)
        if len(client) != 0:
            return client
        else:
            return None

    def RandomClient(self):
        Client_List = []
        ok = 0
        while True:
            random_id = "".join(random.choices(string.digits, k=3))
            random_name = "".join(random.choices(string.ascii_letters, k=5))
            random_cnp = "".join(random.choices(string.digits, k=13))
            random_surname = "".join(random.choices(string.ascii_letters, k=10))
            client = Client(random_id, random_name, random_surname, random_cnp)
            if client in Client_List:
                    ok = 0
            else:
                Client_List.append(client)
                return client
            if ok == 0:
                break

    def getAll(self):
        return self.repository.getAll()