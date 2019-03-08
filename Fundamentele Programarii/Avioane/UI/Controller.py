class Controller:
    def __init__(self,repository):
        self.repo = repository

    def remove_flights(self,company):
        list = self.repo.get_by_name(company)
        if list ==[]:
            raise ValueError("There are no flights from this company")
        for i in list:
            self.repo.__delete__(i.getFlight())


    def flights(self):
        return self.repo.getAll()