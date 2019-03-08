import unittest
from Domain.Flight import Flight
class Validator:

    def validate(self,flight):
        error = " "
        if flight.getFlight() == ' ':
            error = error + "Flight number can not be empty"
        if flight.getCompany() == ' ':
            error = error + "Company name can not be empty"
        if flight.getCity() == ' ':
            error = error + "City can not be empty"
        if len(error)>0:
            raise ValueError(error)
        return True

class TestCase(unittest.TestCase):

    def setUp(self):
        self.flight = Flight(1,'DEPARTURE','WIzz','LA')
        self.f1 =  Flight(' ','ARRIVAL','WIzz','LA')
        self.f2 = Flight(1, 'DEPARTURE', '', 'LA')
        self.f3 = Flight(3, 'ARRIVAL', 'WIzz', '')
        self.validator = Validator()
    def tearDown(self):
        pass

    def testValidator(self):
        self.assertRaises(ValueError,self.validator.validate,self.flight)

    def testValidator1(self):
        self.assertRaises(ValueError,self.validator.validate,self.f1)

    def testValidator2(self):
        self.assertRaises(ValueError,self.validator.validate,self.f2)

    def testValidator3(self):
        self.assertRaises(ValueError,self.validator.validate,self.f3)

    def testValidator4(self):
        self.assertRaises(KeyError, Flight,1,'CEVA','ana','mare')

    def testValidator6(self):
        flight = Flight(1,'ARRIVAL','a','b')
        self.assertEqual(flight.getType().name,'ARRIVAL')

if __name__ == '__main__':
    unittest.main()

