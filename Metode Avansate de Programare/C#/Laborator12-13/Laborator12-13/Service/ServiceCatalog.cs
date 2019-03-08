using System;
using System.Collections.Generic;
using System.Text;
using System.Text;
using Laborator12_13.repository;
using Laborator12_13.domain;
using Laborator12_13.Validator;
using System.Globalization;
using System.Linq;

namespace Laborator12_13.Service
{
    class ServiceCatalog
    {
        ICrudRepository<int, Inregistrare> catalogRepo;
        ICrudRepository<int, Tema> temaRepo;
        ICrudRepository<int, Student> studentRepo;

        public ServiceCatalog(ICrudRepository<int, Inregistrare> catalogRepo, ICrudRepository<int, Tema> temaRepo, ICrudRepository<int, Student> studentRepo)
        {
            this.catalogRepo = catalogRepo;
            this.temaRepo = temaRepo;
            this.studentRepo = studentRepo;
        }

        public int getSaptamana(DateTime now)
        {
            CultureInfo ci = CultureInfo.CurrentCulture;
            int weekNumber = ci.Calendar.GetWeekOfYear(now, CalendarWeekRule.FirstFourDayWeek, DayOfWeek.Monday);
            return weekNumber;
        }
        public int nrNote()
        {
            IEnumerable<Inregistrare> it = catalogRepo.findAll();
            int nr = 0;
            foreach (Inregistrare i in it)
            {
                nr++;
            }
            return nr;
        }

        public int nrTeme()
        {
            IEnumerable<Tema> it = temaRepo.findAll();
            int nr = 0;
            foreach (Tema i in it)
            {
                nr++;
            }
            return nr;
        }
        public int nrStud()
        {
            IEnumerable<Student> it =studentRepo.findAll();
            int nr = 0;
            foreach (Student i in it)
            {
                nr++;
            }
            return nr;
        }
        public int GetLabNumber()
        {
            int currentWeek = getSaptamana(DateTime.Now);
            int dif = currentWeek - 39;
            if (dif < 1 || dif > 16) return default(int);
            if (currentWeek.Equals(13) || currentWeek.Equals(14))
                return 12;
            if (currentWeek.Equals(15)) return dif - 1;
            if (currentWeek.Equals(16)) return dif - 2;
            return dif;
        }

        public void addNotaCuPenalizare(int idS, int idT, float n, String feedback)
        {
            int id = nrNote() + 1;
            if (studentRepo.findOne(idS) != null)
            {
                if (temaRepo.findOne(idT) != null)
                {
                    String nume = studentRepo.findOne(idS).Nume;
                    Tema t = temaRepo.findOne(idT);
                    if (t.Deadline < GetLabNumber())
                    {
                        int diff = GetLabNumber() - t.Deadline;
                        for (int j = 1; j <= diff; j++)
                            n = (float)(n - 2.5);
                    }
                    if (n < 0)
                        n = 0;
                    Inregistrare i = new Inregistrare(id, idS, idT, n);
                    IEnumerable<Inregistrare> it = catalogRepo.findAll();
                    foreach (Inregistrare inr in it)
                    {
                        if (inr.IdStudent == idS && inr.IdTema == idT)
                            throw new ValidationException("Studentul are deja o nota la aceasta tema \n");
                    }
                    catalogRepo.save(i);

                }
                else
                {
                    throw new ValidationException("Id-ul dat pentru tema este inexistent \n");
                }
            }
            else
            {
                throw new ValidationException("Id-ul dat pentru student este inexistent \n");
            }
        }

        public void addNotaFaraPenalizare(int idS, int idT, float n, String feedback)
        {
            int id = nrNote() + 1;
            if(studentRepo.findOne(idS)!=null) {
                if (temaRepo.findOne(idT) != null) {
                    String nume = studentRepo.findOne(idS).Nume;
                    Tema t = temaRepo.findOne(idT);
                    Inregistrare i = new Inregistrare(id, idS, idT, n);
                    IEnumerable<Inregistrare> it = catalogRepo.findAll();
                    foreach (Inregistrare inr in it)
                    {
                        if (inr.IdStudent == idS && inr.IdTema == idT)
                             throw new ValidationException("Studentul are deja o nota la aceasta tema \n");
                    }
                    catalogRepo.save(i);
                }
                else
                {
                throw new ValidationException("Id-ul dat pentru tema este inexistent \n");
                }
            }
            else {
                throw new ValidationException("Id-ul dat pentru student este inexistent \n");
            }
        }

        public IEnumerable<Inregistrare> allNote()
        {
            return catalogRepo.findAll();
        }

        private List<Inregistrare> FilterBy(Predicate<Inregistrare> predicate)
        {
            List<Inregistrare> angajati = catalogRepo.findAll().ToList();
            return angajati.Where((x, y) => { return predicate(x); }).ToList();
        }

        public List<Inregistrare> FilterByStudent(int level)
        {
            return FilterBy(x => x.IdStudent.Equals(level));
        }

        /**
         * Media fiecarui student la laborator
         */
        public IDictionary<int, float> MedieStudent()
        {
            int nr = nrTeme();
            var rez = (from n in catalogRepo.findAll().Distinct()
                       group n.idStudent by n.idStudent into gr
                       select gr.First()).Distinct().ToList();

            IDictionary<int, float> dic = new Dictionary<int, float>();
            rez.ForEach(stu =>
            {
                var media = catalogRepo.findAll().ToList()
                    .Where(n => n.idStudent.Equals(stu))
                    .Sum(no => no.nota);
                dic.Add(stu, media / nr);

            });
            return dic;
        }

        public IDictionary<int, float> MedieTeme()
        {
            int nr = nrStud();
            var rez = (from n in temaRepo.findAll().Distinct()
                       group n.NrTema by n.NrTema into gr
                       select gr.First()).Distinct().ToList();

            IDictionary<int, float> dic = new Dictionary<int, float>();
            rez.ForEach(stu =>
            {
                var media = catalogRepo.findAll().ToList()
                    .Where(n => n.idTema.Equals(stu))
                    .Sum(no => no.nota);

                dic.Add(stu, media / nr);

            });
            return dic;
        }
    }
}
