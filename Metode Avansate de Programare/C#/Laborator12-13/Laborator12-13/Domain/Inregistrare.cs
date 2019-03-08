using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator12_13.domain
{
    public class Inregistrare : IHasID<int>
    {
        private int id;
        public int idStudent;
        public int idTema;
        public float nota;

        public Inregistrare(int id, int idStudent, int idTema, float nota)
        {
            this.id = id;
            this.IdStudent = idStudent;
            this.IdTema = idTema;
            this.Nota = nota;
        }

        public int ID { get => id; set => id=value; }
        public int Id { get => id; set => id = value; }
        public int IdStudent { get => idStudent; set => idStudent = value; }
        public int IdTema { get => idTema; set => idTema = value; }
        public float Nota { get => nota; set => nota = value; }

        public override bool Equals(object obj)
        {
            var inregistrare = obj as Inregistrare;
            return inregistrare != null &&
                   id == inregistrare.id &&
                   idStudent == inregistrare.idStudent &&
                   idTema == inregistrare.idTema &&
                   nota == inregistrare.nota;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(id, idStudent, idTema, nota);
        }
        public override string ToString()
        {
            return Id + "," + IdStudent + "," + IdTema + "," + Nota;
        }
    }
}
