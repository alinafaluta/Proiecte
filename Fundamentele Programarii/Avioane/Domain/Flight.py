from enum import Enum

class Flight_type(Enum):
    ARRIVAL = 1
    DEPARTURE = 2

class Flight:
    def __init__(self,flight_number,type,company_name,city):
        self.__flight = flight_number
        self.__type = Flight_type[type]
        self.__enum = type
        self.__company = company_name
        self.__city = city

    def getFlight(self):
        return self.__flight

    def getType(self):
        return self.__type.name
    def getCompany(self):
        return self.__company
    def getCity(self):
        return self.__city

    def __str__(self):
        return 'Flight Number: '+str(self.__flight) +" "+str(self.getType()) +" "+str(self.__company) +" "+str(self.__city)


def test():
    zbor = Flight(1,'DEPARTURE','AirBus','LA')
    assert zbor.getCity() == 'LA'
    assert zbor.getCompany() == 'AirBus'
    assert zbor.getFlight() == 1

test()
