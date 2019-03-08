using System;
using System.Collections.Generic;
using System.Text;
using Laborator12_13.repository;
using Laborator12_13.domain;
using Laborator12_13.Validator;
using System.Linq;

namespace Laborator12_13.Service
{
    class ServiceTema
    {
        ICrudRepository<int, Tema> temaRepo;

        public ServiceTema(InMemoryRepository<int, Tema> temaRepo)
        {
            this.temaRepo = temaRepo;
        }

        public ServiceTema(ICrudRepository<int, Tema> temaRepo)
        {
            this.temaRepo = temaRepo;
        }

        public void addTema(int nrTema, String descriere, int deadline, int predare)
        {
            Tema t = new Tema(nrTema, descriere, deadline, predare);
            if (temaRepo.findOne(nrTema) != null)
                throw new ValidationException("Tema exista deja");
            temaRepo.save(t);
        }

        public void updateTema(int nrTema, String descriere, int deadline, int predare)
        {
            Tema t = new Tema(nrTema, descriere, deadline, predare);
            if (temaRepo.update(t) != null)
            {
                throw new ValidationException("Tema pe care doriti sa o actualizati nu exista! \n");
            }
        }

        public IEnumerable<Tema> allTeme()
        {
            return temaRepo.findAll();
        }
        public Tema findTema(int id)
        {
            return temaRepo.findOne(id);
        }

        private List<Tema> FilterBy(Predicate<Tema> predicate)
        {
            List<Tema> angajati = temaRepo.findAll().ToList();
            return angajati.Where((x, y) => { return predicate(x); }).ToList();
        }

        public List<Tema> FilterByDeadline(int level)
        {
            return FilterBy(x => x.Deadline<level);
        }
    }
}
