using System;

using Laborator12_13.Service;
using Laborator12_13.domain;
using Laborator12_13.Validator;
using Laborator12_13.repository;
using Laborator12_13.Controller;
using Laborator12_13.Repository;
using Laborator12_13.Domain;

namespace Laborator12_13
{
    class Program
    {
        static void Main(string[] args)
        {
            IValidator<Student> validatorS = new ValidareStudent();
            IValidator<Tema> validatorT = new ValidareTema();
            IValidator<Inregistrare> validatorN = new ValidatorNota();

            ICrudRepository<int, Student> repoS = new InMemoryRepository<int, Student>(validatorS);
            ICrudRepository<int, Tema> repoT
                = new InMemoryRepository<int, Tema>(validatorT);
            ICrudRepository<int, Inregistrare> repoN
                = new InMemoryRepository<int,Inregistrare>(validatorN);
            string filenameS = "D://Facultate Anul II//Metode Avansate de Programare//Laborator12-13//Laborator12-13//Date//Studenti.txt";
            string filenameT = "D://Facultate Anul II//Metode Avansate de Programare//Laborator12-13//Laborator12-13//Date//Teme.txt";
            string filenameC = "D://Facultate Anul II//Metode Avansate de Programare//Laborator12-13//Laborator12-13//Date//Catalog.txt";
            ICrudRepository<int, Student> repoSF = new StudentInFileRepository(validatorS, filenameS);
            ICrudRepository<int, Tema> repoTF = new TemeInFileRepository(validatorT, filenameT);
            ICrudRepository<int, Inregistrare> repoNF = new NoteInFileRepository(validatorN, filenameC);
            ServiceStudent serviceS = new ServiceStudent(repoSF);
            ServiceTema serviceT = new ServiceTema(repoTF);
            ServiceCatalog serviceC = new ServiceCatalog(repoNF, repoTF, repoSF);
            Controler controller = new Controler(serviceC, serviceS, serviceT);
            controller.run();
        }
    }
}
