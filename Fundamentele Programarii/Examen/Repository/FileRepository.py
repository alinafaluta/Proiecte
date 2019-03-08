from Domain.Jucator import Jucator,Validator
import unittest

class JucatoriRepo:

    def __init__(self,fileName):
        self.__file = fileName

    def LoadFromFile(self):
        '''
            Functia citeste toate datele aflate in fisier
        :return: o lista cu jucatori
        :raises IOError daca nu poate deschide fisierul
        '''
        try:
            f = open(self.__file,'r')
        except IOError:
            return
        rez = []
        line = f.readline().strip()

        while line !='':
            date = line.split(',')
            jucator = Jucator(date[0],date[1],date[2],date[3])
            rez.append(jucator)
            line = f.readline().strip()
        f.close()
        return rez

    def add(self,nume,prenume,inaltime,post):
        """
            valideaza un jucator si il adauga  in fiser daca e valid
        :param jucator: Jucator
        :returns True daca adugarea a fost realizata or False daca jucatorul exista deja
        :raises ValueError daca jucatorul nu este valid
        """
        jucator = Jucator(nume,prenume,inaltime,post)
        try:
            if Validator().validare(jucator)== True:
                players = self.LoadFromFile()
                ok=1
                for el in players:
                    if el.getNume()== nume and el.getPrenume()==prenume:
                        ok=0
                if ok == 1:
                    players.append(jucator)
                    self.StoreToFile(players)
                else:
                    return False
        except ValueError as er:
            return er
        return True

    def StoreToFile(self,list):
        """
        adauga jucatori in fisier
        :param list: lista cu jucatori
        """
        with open(self.__file,'w') as f:
            for player in list:
                string = str(player.getNume())+","+str(player.getPrenume())+","+str(player.getHigh())+","+str(player.getPost())+'\n'
                f.write(string)

    def modifica(self,nume,prenume,high):
        """
        functia cauta jucatorul caruia i se vor actualiza datele si apoi inlocuieste inaltimea actuala cu cea data
        :param nume: numele jucatorului
        :param prenume: prenumele jucatorului
        :param high: noua inaltime
        :return: jucatorul modificat
        """
        game = self.LoadFromFile()
        new = []
        for player in game:
            if player.getNume() == nume and player.getPrenume() == prenume:
                player.setHigh(high)
                one = player
            new.append(player)
        self.StoreToFile(new)
        return one

    def getAll(self):
        """
        :return: toti jucatorii din fisier
        """
        return self.LoadFromFile()

    def __sizeof__(self):
        """
        :return: numarul de jucatori din fisier
        """
        return len(self.LoadFromFile())

def test():
    repo = JucatoriRepo("test.txt")
    repo.add(Jucator('Ionut','Popescu',178,'Fundas'))
    repo.add(Jucator('Tudor','Dmitrescu', 186,'Extrema'))
    assert repo.__sizeof__() == 7

    repo.modifica('Ionut','Popescu',200)

