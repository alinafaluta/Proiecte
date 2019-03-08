class Client:

    def __init__(self, id, name, surname, nin):
        self.__id = id
        self.__name = name
        self.__surname = surname
        self.__nin = nin

    def getId(self):
        """
            :return the id of the current client as an int value
        """
        return self.__id

    def getName(self):
        """
            :return the name of the current client as a string
        """
        return self.__name

    def getSurname(self):
        """
            :return the surname of the current client as a string
        """
        return self.__surname

    def getNIN(self):
        """
            :return the NIN (National Identification Number) of the current client as a string
        """
        return self.__nin

    def __str__(self):
        return "ID:"+str(self.__id)+" Last name: "+str(self.__name)+" First name: "+str(self.__surname)+" NIN:"+str(self.__nin)

    def setName(self,new):
        """
            gives the curent client the new name
        """
        self.__name = new

    def setSurname(self,surnameNew):
        """
            gives the curent client the new surname
        """
        self.__surname = surnameNew

    def setNIN(self,newNIN):
        """
            gives the curent client the new nin
        """
        self.__nin = newNIN



class ClientValidator:
    """
        Class responsible with validation
    """

    def validate(self, client):
        """
            Validate a client
            client - client
            raise ValueError
            if: Id, name, surname or NIN is empty
            if: nin longer than 13 or shorter that 13
        """

        errors = ""
        if client.getId() == "":
            errors += "Id can not be empty "
        if client.getName() == "":
            errors += "Name can not be empty "
        if client.getSurname() == "":
            errors += "Surname can not be empty "
        if client.getNIN() == "":
            errors += "NIN can not be empty "
        if len(client.getNIN()) < 13:
            errors += "NIN can not have less than 13 digits "
        if len(client.getNIN()) > 13:
            errors += "NIN can not have more than 13 digits "
        if type(client.getNIN()) == float:
            errors += "NIN can not be float "
        if not client.getNIN().isdigit():
            errors +="NIN has to have 13 digits "
        if len(errors) > 0:
            raise ValueError(errors)
        return True

def testClientValidator():
    """
            Test validate
    """
    validator = ClientValidator()
    client  =  Client("", "Ion", "str","")
    try:
        validator.validate(client)
        assert False
    except ValueError:
        assert True

    client = Client("", "", "", "")
    try :
        validator.validate(client)
        assert False
    except ValueError:
        assert True

    client = Client(2, "Ana", "Buzincu", "1234567891234")
    try:
        validator.validate(client)
    except ValueError:
        assert True

testClientValidator()


