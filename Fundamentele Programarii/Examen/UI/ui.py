class UIJucator:

    def __init__(self,controller):
        self.controller = controller

    def start(self):
        print('1.Afisare jucatori')
        print('2.Adaugare jucator')
        print('3.Modificare inaltime')
        print('4.Formare Echipa')
        print('5.Importare jucatori')
        print('x.Inchide aplicatia')

    def com_1(self):
        """
        afiseaza jucatorii
        """
        list = self.controller.afisare()
        for player in list:
            print(player)

    def com_2(self):
        """
        adauga jucator
        """
        nume = input("Dati numele jucatorului: ")
        prenume = input("Dati prenumele jucatorului: ")
        inaltime = input("Dati inaltimea: ")
        post = input("Dati postul ocupat: ")
        ok = 1
        if post != 'Fundas':
            if post !='Pivot':
                if post != 'Extrema':
                   print('Postul poate fi decat Fundas/Pivot/Extrema. ')
                elif int(inaltime)<=0:
                    print("Inaltimea nu poate fi negativa")
                else:
                    if self.controller.adaugare(nume,prenume,inaltime,post) == True:
                        print("Jucatorul a fost adaugat")
                    elif self.controller.adaugare(nume,prenume,inaltime,post) == False:
                        print("Jucator existent")
                    else:
                        print(self.controller.adaugare(nume,prenume,inaltime,post))

    def com_3(self):
        """
        modifica inaltimea unui jucator
        """
        nume = input("Dati numele jucatorului: ")
        prenume = input("Dati prenumele jucatorului: ")
        inaltime = input("Dati inaltimea noua: ")
        try:
            player = self.controller.modificare(nume,prenume,inaltime)
            print('Jucatorul '+str(player.getNume())+" "+str(player.getPrenume())+' a fost modificat')
        except ValueError as er:
            print(er)

    def com_4(self):
        """
        afiseaza echipa
        """
        try:
            echip = self.controller.echipa()
            print("Echipa:")
            for i in echip:
                print(i)
        except ValueError as er:
            print(er)

    def com_5(self):
        """
        importa jucatori din alt fisier
        """
        file = input("Dati numele fisierului: ")
        nr = self.controller.importare(file)
        self.com_1()
        print("Au fost importati "+str(nr)+" jucatori")

    def main(self):
        self.start()
        cmd = input("Dati comanda:")
        while cmd !='x':
            if cmd == '1':
                self.com_1()
            if cmd == '2':
                self.com_2()
            if cmd == '3':
                self.com_3()
            if cmd == '4':
                self.com_4()
            if cmd == '5':
                self.com_5()
            cmd = input("Dati comanda:")
        if cmd == 'x':
            print('Bye')
