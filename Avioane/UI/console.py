class UI:
    def __init__(self,control):
        self.controler = control

    def start(self):
        print("1-afisare")
        print("2-stergere")
        print("x-exit")

    def com_1(self):
        try:
            print("Flights:")
            list = self.controler.flights()
            if list == []:
                print('No more flights.The list is empty')
            for i in list:
                print(i)
        except ValueError as er:
            print(er)
    def com_2(self):
        try:
            company = input("Give the name of the company")
            self.controler.remove_flights(company)
        except ValueError as er:
            print(er)

    def main(self):
        cmd = 0
        while cmd!='x':
            self.start()
            cmd = input('Give the comand: ')
            if cmd =='1' :
                self.com_1()
            if cmd =='2':
                self.com_2()
        if cmd == 'x':
            print('bye')