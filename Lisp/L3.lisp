;5. Definiti o functie care testeaza apartenenta unui nod intr-un arbore n-ar
 ;reprezentat sub forma (radacina lista_noduri_subarb1... lista_noduri_
 ;_subarbn)
 ;Ex: arborelele este (a (b (c)) (d) (e (f))) si nodul este 'b => adevarat

 (defun ap (e a)
 	(cond
 		((null a) 0)
 		((and (atom a) (equal a e)) 1)
 		((atom a) 0)
 		(t (apply #'+ (mapcar #'(lambda (k)
 									( ap e k)
 								) 
 										a)
 		   )
 		)
 	)
)

(defun apare (e ar)
	(cond
	((> (ap e ar) 0) t) 
		))

;L1
;1.
;a) Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea,
;al 6-lea,....element.
(defun unuA (l a poz)
	(cond
		((null l) nil)
		(( = (mod poz 2) 0) (append (list (car l) a) (unuA (cdr l) a (+ poz 1))))
		(t (cons (car l) (unuA (cdr l) a (+ poz 1))))
	)
)

(defun unuAW (l a)
	(unuA l a 1)
	)
;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;care apar, pe orice nivel, dar in ordine inversa. De exemplu: (((A B) C)
;(D E)) --> (E D C B A)
(defun unuB (l rez)
	(cond
		((null l) rez)
		((atom (car l)) (unuB (cdr l) (cons (car l) rez) ))
		((listp (car l)) (append (unuB (cdr l) rez) (unuB (car l) rez)))
		(t (unuB (cdr l) rez))
	)
)

(defun unuBW (l)
	(unuB l nil)
)
;c) Definiti o functie care intoarce cel mai mare divizor comun al numerelor
;dintr-o lista neliniara.
(defun cmmdc(a b)
	(cond
		((= a b) a)
		((= a 0) b)
		((= b 0) a)
		((> a b) (cmmdc (- a b) b))
		((< a b) (cmmdc (- b a) a))
	)
)

(defun unuC (l rez)
	(cond 
		((null l) rez)
		((listp (car l)) (unuC (car l) rez) )
	)
)
;d) Sa se scrie o functie care determina numarul de aparitii ale unui atom dat
;intr-o lista neliniara.

(defun numarAparitii( l a)
	(cond 
		((null l) 0)
		((equal (car l) a) (+ 1 (numarAparitii (cdr l) a)))
		((listp (car l)) (+ (numarAparitii (car l) a) (numarAparitii (cdr l) a) ))
		(t (numarAparitii (cdr l) a))
	)
)

;2.
;a) Definiti o functie care selecteaza al n-lea element al unei liste, sau
;NIL, daca nu exista.

(defun doiA (l n poz)
	(cond
		((null l)nil)
		((= poz n) (car l))
		(t (doiA (cdr l) n (+ poz 1)))
	)
)

(defun doiAW ( l n)
	(doiA l n 1))
;b) Sa se construiasca o functie care verifica daca un atom e membru al unei
;liste nu neaparat liniara.
(defun doiB (l a)
	(cond
		((null l) nil)
		((equal (car l) a) t)
		((listp (car l)) (or (doiB (car l)a) (carB (cdr l) a)))
		(t (doiB (cdr l) a))	
	)
)
;c) Sa se construiasca lista tuturor sublistelor unei liste. Prin sublista se
;intelege fie lista insasi, fie un element de pe orice nivel, care este
;lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
;( (1 2 (3 (4 5) (6 7)) 8 (9 10)) (3 (4 5) (6 7)) (4 5) (6 7) (9 10) ).
;( (1 2 (3 (4 5) (6 7)) 8 (9 10)) ((3 (4 5) (6 7)) (4 5) (6 7) (9 10)) )
(defun doiC (l)
	(cond 
		((null l) nil)
		((listp (car l)) (append (cons(car l) (doiC (car l))) (doiC (cdr l))))
		(t (doiC (cdr l)))
 	)
)
(defun doiCW (l)
	(append l (doiC l)))
;d) Sa se scrie o functie care transforma o lista liniara intr-o multime.
(defun elimina_el( l e)
	(cond 
		((null l)nil)
		((not(equal (car l) e)) (cons (car l) (elimina_el (cdr l) e)) )
		(t (elimina_el (cdr l) e))
	)
)

(defun multime (l)
	(cond
		((null l) nil)
		( (= (numarAparitii l (car l)) 1) (append (list(car l)) (multime (cdr l))))
		(t (cons (car l) (multime (elimina_el (cdr l) (car l)))))
	)
)

;3.
;a) Definiti o functie care intoarce produsul a doi vectori.
(defun produs ( l)
	(cond
		((null l) 1)
		( t (* (Car l) (produs (cdr l))))
	)
)
(defun produs2 ( l k)
	(* (produs l) (produs k)))
;b) Sa se construiasca o functie care intoarce adancimea unei liste.
(defun maxim( a b)
	(cond
		((> a b) a)
		(t b)
	))

(Defun adancime (l)
	(cond
		((null l) 1)
		((listp (car l)) (maxim (+ (adancime(car l)) 1) (adancime (cdr l)) ))
		(t (adancime (cdr l)))
	)
)

;c) Definiti o functie care sorteaza fara pastrarea dublurilor o lista
;liniara.
(defun minim_lista_aux(l m)
	(cond
		((null l) m)
		((> m (car l)) (minim_lista_aux (cdr l) (car l)))
		(t (minim_lista_aux (cdr l) m) )
	)
)

(defun sortare (l)
	(cond
		((null l) nil)
		(t (append (list(minim_lista_aux l 1000)) (sortare (elimina_el l (minim_lista_aux l 1000) ))))
	)
)
;d) Sa se scrie o functie care intoarce intersectia a doua multimi.
(defun intersectie(l k)
	(cond
		((null l) nil)
		((null k) nil)
		((=(numarAparitii k (car l)) 1) (append (list(car l)) (intersectie (cdr l) k)))
		(t (intersectie (cdr l) k))
	)
)

;4.
;a) Definiti o functie care intoarce suma a doi vectori.
(defun suma(l)
	(cond
		((null l) 0)
		(t (+ (Car l) (suma (cdr l))))
	)
)

(defun suma2 ( l k)
	(+ (suma l) (suma k)))
;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;care apar, pe orice nivel, dar in aceeasi ordine. De exemplu:
;(((A B) C) (D E)) --> (A B C D E)
(defun lista_atomi(l )
	(cond
		((null l) nil)
		((listp (car l)) 
			(append (lista_atomi (car l)) (lista_atomi (cdr l))))
		(T (cons (car l) (lista_atomi (cdr l))))	
		)
	)
;c) Sa se scrie o functie care plecand de la o lista data ca argument,
;inverseaza numai secventele continue de atomi. Exemplu:
;(a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))
(defun invers_lista (l)
	(cond
		((null l) nil)
		(T (append (invers_lista(cdr l)) (list(car l))))
	)
)
(defun cautaPrimaSecventaConsec_aux(l ok col)
	(cond
		((null l) col)
		((and (listp (car l)) (= ok 0)) (cautaPrimaSecventaConsec_aux (cdr l) ok col))
		((atom(car l)) (cautaPrimaSecventaConsec_aux (cdr l) 1 (append col (list(car l)))))
		(t col)
	)
)

(defun cautaPrimaSecventaConsec (l)
	(cautaPrimaSecventaConsec_aux l 0 nil))

(defun da_poz_sfarsit_aux(l k poz)
	(cond 
		((equal (car l) k) poz)
		(t (da_poz_sfarsit_aux (cdr l) k (+ poz 1)))
	)
)

(defun da_sf_aux(l el)
	(cond
		((null l) el)
		(t (da_sf_aux (cdr l) (car l)))
	)
)

(defun da_sf (l)
	(da_sf_aux l 0))

(defun da_poz_sfarsit(l k)
	(da_poz_sfarsit_aux l (da_sf k) 1))


(defun retDeLaPoz_aux(l p po)
	(cond
		((= p po) l)
		(t (retDeLaPoz_aux (cdr l) p (+ po 1)))
	)
)

(defun retDeLaPoz (l p)
	(retDeLaPoz_aux l p 1))

(defun sfarsit(l)
	(+ (da_poz_sfarsit l (cautaPrimaSecventaConsec l)) 1))

(defun invers (l)
	(cond
		((null l) nil)
		((listp(car l)) (cons (invers (car l)) (invers (cdr l))))
		(t (append (invers_lista(cautaPrimaSecventaConsec l)) (invers (retDeLaPoz l (sfarsit l) ))))
	)
)
;d) Sa se construiasca o functie care intoarce maximul atomilor numerici
;dintr-o lista, de la nivelul superficial.

(defun maxim_superficial ( L M)
	(cond
		((null L) M)
		( (listp (car L)) 
			(maxim_superficial (cdr L) M))
		((and (numberp (car L)) (> (Car L) M)) 
			(maxim_superficial (cdr L) (car L)))
		( T (maxim_superficial (cdr L) M))
	)
)

;a) Definiti o functie care interclaseaza cu pastrarea dublurilor doua liste
;liniare sortate.
(Defun interclasare ( l k)
	(cond
		((null l) k)
		((null k) l)
		((equal (car l) (car k)) (append (list(car l)) (list (car k)) (interclasare (cdr l) (cdr k))))
		((> (car l) (car k)) (append (list (car k)) (interclasare l (cdr k))))
		((< (car l) (car k)) (append (list(car l)) (interclasare (cdr l) k)))
	)
)
;b) Definiti o functie care substituie un element E prin elementele unei liste
;L1 la toate nivelurile unei liste date L.
(defun inlocuire (l e k)
	(cond
		((null l) nil)
		((equal (car l) e) (append k (inlocuire (cdr l) e k)))
		((listp (car l)) (append (inlocuire (car l) e k) (inlocuire (cdr l) e k)))
		( t  (append (list(car l)) (inlocuire (cdr l) e k)))
	)
)
;c) Definiti o functie care determina suma a doua numere in reprezentare de
;lista si calculeaza numarul zecimal corespunzator sumei.
;d) Definiti o functie care intoarce cel mai mare divizor comun al numerelor
;dintr-o lista liniara.
;6.
;a) Sa se scrie de doua ori elementul de pe pozitia a n-a a unei liste
;liniare. De exemplu, pentru (10 20 30 40 50) si n=3 se va produce (10 20
;30 30 40 50).
(defun de_doua_ori( l p k)
	(cond
		((null l) nil)
		((= p k) (append (list (car l)) (list(car l)) (de_doua_ori (cdr l) p (+ k 1))))
		( t (append (list (car l)) (de_doua_ori (cdr l) p (+ k 1))) )
	)
)
(defun de_doua_oriW (l n)
	(de_doua_ori l n 1))
;b) Sa se scrie o functie care realizeaza o lista de asociere cu cele doua
;liste pe care le primeste. De ex: (A B C) (X Y Z) --> ((A.X) (B.Y)
;(C.Z)).
(Defun perechi( l k)
	(cond
		((null l) nil)
		((null k) nil)
		(t (cons(cons (car l) (car k)) (perechi (cdr l) (cdr k)))) 
	)
)
;c) Sa se determine numarul tuturor sublistelor unei liste date, pe orice
;nivel. Prin sublista se intelege fie lista insasi, fie un element de pe
;orice nivel, care este lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
;5 (lista insasi, (3 ...), (4 5), (6 7), (9 10)).
(defun subli( l)
	(cond
		((null l) nil)
		((listp (car l)) (append (cons (car l) (subli(car l))) (subli (cdr l))))
		(t (subli (cdr l)))
	)
)
(defun sublist(l)
	(cons l (subli l)))
;d) Sa se construiasca o functie care intoarce numarul atomilor dintr-o lista,
;de la nivel superficial.
(defun nrAtomi (l)
	(cond
		((null l) 0)
		((atom (car l)) (+ 1 (nrAtomi(cdr l))))
		(t (nrAtomi (cdr l)))
	)
)

;7.
;a) Sa se scrie o functie care testeaza daca o lista este liniara.
(Defun eLiniara(l)
	(cond
		((null l) t)
		((listp (car l)) nil)
		( t (eLiniara(cdr l)))
))
;b) Definiti o functie care substituie prima aparitie a unui element intr-o
;lista data.
(defun inlocPrima (l e k)
	(cond
		((null l) nil)
		( (equal (car l) e) (append (list k) (cdr l) (inlocPrima nil e k)))
		( t (append (list(car l)) (inlocPrima (cdr l) e k)))
	)
)
;c) Sa se inlocuiasca fiecare sublista a unei liste cu ultimul ei element.
;Prin sublista se intelege element de pe primul nivel, care este lista.
;Exemplu: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c
;f)
(defun ultimul_elem ( l k)
	(cond
		((null l) k)
		( t (ultimul_elem (cdr l) (car l)))
	)
)

(defun inlocuire (l)
	(cond
		((null l)nil)
		((listp (Car l))(cons (ultimul_elem (car l) nil) (inlocuire (cdr l))))
		( t (cons (car l) (inlocuire (cdr l))))
	)
)
(defun inlocuireB (l)
	(cond
		((eLiniara (inlocuire l)) l)
		( (not(eLiniara(inlocuire l))) (inlocuire (inlocuire l)))
))

;(a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)
;d) Definiti o functie care interclaseaza fara pastrarea dublurilor doua liste
;liniare sortate.

(defun sortarefara (l k)
	(multime (interclasare l k) ))