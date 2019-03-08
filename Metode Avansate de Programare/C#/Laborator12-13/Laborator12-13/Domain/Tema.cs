using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator12_13.domain
{
    public class Tema : IHasID<int>
    {
        int nrTema;
        String descriere;
        int deadline;
        int predare;
        
        public Tema(int nrTema, string descriere, int deadline, int predare)
        {
            this.nrTema = nrTema;
            this.descriere = descriere;
            this.deadline = deadline;
            this.predare = predare;
        }

        public int ID { get => nrTema; set => nrTema = value;}
        public int NrTema { get => nrTema; set => nrTema = value; }
        public string Descriere { get => descriere; set => descriere = value; }
        public int Deadline { get => deadline; set => deadline = value; }
        public int Predare { get => predare; set => predare = value; }

        public override bool Equals(object obj)
        {
            var tema = obj as Tema;
            return tema != null &&
                   nrTema == tema.nrTema &&
                   descriere == tema.descriere &&
                   deadline == tema.deadline &&
                   predare == tema.predare;
        }

        public override int GetHashCode()
        {
            return HashCode.Combine(nrTema, descriere, deadline, predare);
        }

        public override string ToString()
        {
            return ID + "," + Descriere + "," + Deadline + "," + Predare;
        }
    }
}
