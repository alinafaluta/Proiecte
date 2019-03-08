using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator12_13.domain;
using Laborator12_13.Validator;
using Laborator12_13.repository;

namespace Laborator12_13.repository
{
    public class InMemoryRepository<ID, E> : ICrudRepository<ID, E> where E : IHasID<ID>
    {

        public IDictionary<ID, E> entities = new Dictionary<ID, E>();
        private IValidator<E> validator;

        public InMemoryRepository(IValidator<E> validator)
        {
            this.validator = validator;
        }

        public E delete(ID id)
        {
            if (id == null)
                throw new ArgumentNullException("Parametrul este null");
            if (!entities.ContainsKey(id))
                return default(E);
            else
            {
                E temp = entities[id];
                entities.Remove(id);
                return temp;
            }
        }

        public IEnumerable<E> findAll()
        {
            return entities.Values.ToList();
        }

        public E findOne(ID id)
        {
            if (id == null)
                throw new ArgumentNullException("Parametrul este null");
            if (!entities.ContainsKey(id))
                return default(E);
            else return entities[id];
        }

        public E save(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException("Parametrul este null");
            validator.Validate(entity);
            if (entities.ContainsKey(entity.ID))
                return entity;
            entities[entity.ID] = entity;
            return default(E);
        }

        public E update(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException("Parametrul este null");
            validator.Validate(entity);
            if (!entities.ContainsKey(entity.ID))
                return entity;
            entities[entity.ID] = entity;
            return default(E);
        }
    }
}
