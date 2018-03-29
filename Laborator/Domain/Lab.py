import unittest

class Laboratory:

    def __init__(self,labID,labNumber,problemNumber):
        self.__id = labID
        self.__lab = labNumber
        self.__problem = problemNumber

    def getId(self):
        """
            :return: the id of the lab
        """
        return self.__id

    def getLab(self):
        """
            :return: the number of the laboratory
        """
        return self.__lab

    def getProblem(self):
        """
            :return: the number of the problem
        """
        return self.__problem

    def __str__(self):
        return"ID: " + str(self.__id) + " Laboratory: " + str(self.__lab) + " Problem: " + str(self.__problem)


class Validator():

    def validate(self, lab):
        """
            Validate a lab
            raise ValueError
            if: Id, lab number, problem number is empty
        """

        errors = ""
        if lab.getId() == "":
            errors += "Id can not be empty "
        if lab.getLab() == "":
            errors += "Lab number can not be empty "
        if lab.getProblem() == "":
            errors += "Problem number can not be empty "
        if len(errors) > 0:
            raise ValueError(errors)
        return True


class TestCase(unittest.TestCase):

    def setUp(self):
        self.validator = Validator()
        self.lab1 = Laboratory(1,2,"Avioane")
        self.lab2 = Laboratory('',3,'Avioane')
    def tearDown(self):
        pass

    def testId(self):
        self.assertTrue(self.lab1.getId()==1)

    def testLab(self):
        self.assertEqual(self.lab1.getLab(),2)

    def testProblem(self):
        self.assertTrue(self.lab1.getProblem()=='Avioane')

    def testValidator(self):
        self.assertRaises(ValueError,self.validator.validate,self.lab2)


if __name__ == 'main':
    unittest.main()