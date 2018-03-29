import unittest

class Student:

    def __init__(self,ID,name):
        self.__id = ID
        self.__name = name

    def getId(self):
        """
            :return: the id of the student
        """
        return self.__id

    def getName(self):
        """
            :return: the name of the student
        """
        return self.__name


    def __str__(self):
        return"ID: " + str(self.__id) + " Name: " + str(self.__name)


class Validator():

    def validate(self, student):
        """
            Validate a student
            raise ValueError
            if: Id an name can not be empty
        """

        errors = ""
        if student.getId() == "":
            errors += "Id can not be empty "
        if student.getName() == "":
            errors += "Name can not be empty "
        if len(errors) > 0:
            raise ValueError(errors)
        return True


class TestCase(unittest.TestCase):

    def setUp(self):
        self.validator = Validator()
        self.stud = Student(1,"Ion Popescu")
        self.studi = Student('','Mini martianul')

    def tearDown(self):
        pass

    def testId(self):
        self.assertTrue(self.stud.getId()==1)

    def testName(self):
        self.assertTrue(self.stud.getName()=='Ion Popescu')

    def testValidator(self):
        self.assertRaises(ValueError,self.validator.validate,self.studi)


if __name__ == 'main':
    unittest.main()