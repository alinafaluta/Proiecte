import unittest
class Jucator:

    def __init__(self,nume,prenume,inaltime,post):
        self.__nume = nume
        self.__prenume = prenume
        self.__high = inaltime
        self.__post = post

    def getNume(self):
        """
        :return: numele jucatorului curent
        """
        return self.__nume

    def getPrenume(self):
        """
        :return: prenumele jucatorului curent
        """
        return self.__prenume

    def getHigh(self):
        """
        :return:inaltimea jucatorului curent
        """
        return self.__high

    def getPost(self):
        """
        :return: postul jucatorului curent
        """
        return self.__post

    def setHigh(self,new_high):
        """
        modifica inaltimea jucatorului curent
        """
        self.__high = new_high

    def __str__(self):
        """
        :return: Prenumele,Numele,postul si inaltimea jucatorului
        """
        return str(self.getPrenume())+" "+str(self.getNume())+" "+str(self.getPost())+" "+str(self.getHigh())

class Validator():
    """
     valideaza datele jucatorului
     :raises ValueError daca una dintre date este vida sau daca postul jucatorului este
     diferit de Fundas/Pivot/Extrema sau daca inaltimea nu este int sau este negativa
     :returns True daca datele sunt valide
    """
    def validare(self,jucator):
        errors = ""
        if jucator.getNume() == '':
            errors+="Numele nu poate fi vid. "
        if jucator.getPrenume() == '':
            errors += "Prenumele nu poate fi vid. "
        if jucator.getHigh() =='':
            errors += "Inaltimea nu poate fi vida. "
        if type(jucator.getHigh())== int and int(jucator.getHigh())<0:
            errors += "Inaltimea nu poate fi negativa. "
        if jucator.getPost() == '':
            errors += "Postul nu poate fi vid. "
        if len(errors)==0:
            return True
        else:
            raise ValueError(errors)

class TestCase(unittest.TestCase):

    def setUp(self):
        self.jucator = Jucator('Ionut','Popescu',178,'Fundas')
        self.jucator1 = Jucator('', 'Popescu', 178, 'Fundas')
        self.jucator2 = Jucator('Ionut', '', 178, 'Fundas')
        self.jucator3 = Jucator('Ionut', 'Popescu', 178 ,'')
        self.jucator4 = Jucator('Ionut', 'Popescu','', 'Fundas')
        self.jucator5 = Jucator('Ionut','Popescu',178,'Atacant')
        self.jucator6 = Jucator('Ionut', 'Popescu', -178, 'Fundas')
        self.jucator7 = Jucator('Ionut', 'Popescu', 'a', 'Fundas')
        self.jucator8 = Jucator('Ionut', 'Popescu', 186, 'Fundas')
        self.validator = Validator()

    def tearDown(self):
        pass

    def test_jucator_nume(self):
        """
        testeaza daca clasa ia corect numele jucatorului
        """
        self.assertEqual(self.jucator.getNume(),'Ionut')

    def test_jucator_prenume(self):
        """
        testeaza daca clasa ia corect prenumele jucatorului
        """
        self.assertEqual(self.jucator.getPrenume(),'Popescu')

    def test_jucator_post(self):
        """
        testeaza daca clasa ia corect postul jucatorului
        """
        self.assertEqual(self.jucator.getPost(),'Fundas')

    def test_jucator_inaltime(self):
        """
        testeaza daca clasa ia corect inaltimea jucatorului
        """
        self.assertEqual(self.jucator.getHigh(),178)

    def test_validare_nume_vid(self):
        """
        testeaza semnalarea unei erori daca numele este vid
        """
        self.assertRaises(ValueError,self.validator.validare,self.jucator1)

    def test_validare_prenume_vid(self):
        """
        testeaza semnalarea unei erori daca prenumele este vid
        """
        self.assertRaises(ValueError,self.validator.validare,self.jucator2)

    def test_validare_inaltime_vid(self):
        """
        testeaza semnalarea unei erori daca inaltimea este vida
        """
        self.assertRaises(ValueError,self.validator.validare,self.jucator4)

    def test_validare_post_vid(self):
        """
        testeaza semnalarea unei erori daca postul este
        """
        self.assertRaises(ValueError,self.validator.validare,self.jucator3)


    def test_validare_inaltime_incorecta(self):
        """
        testeaza semnalarea unei erori daca inaltimea este negativa
        """
        self.assertRaises(ValueError,self.validator.validare,self.jucator6)

    def test_validare_inaltime_incorecta2(self):
        """
        testeaza semnalarea unei erori daca inaltimea nu este int
        """
        self.assertRaises(ValueError,self.validator.validare,self.jucator7)
    def test_Set(self):
        """
        verifica daca se modifica inaltimea jucatorului
        """
        self.jucator8.setHigh(186)
        self.assertEqual(self.jucator8.getHigh(), 186)

if __name__ == '__main__':
    unittest.main()