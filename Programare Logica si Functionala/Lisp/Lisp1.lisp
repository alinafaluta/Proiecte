;a) Definiti o functie care intoarce suma a doi vectori
(defun SUMA(L R)
	(cond
		((NULL R) 0)
		((NULL L)
			 (+ (CAR R) (SUMA L (CDR R))))
		(T (+ (car L) (SUMA (CDR L) R )))
	)
)

;b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;care apar, pe orice nivel, dar in aceeasi ordine. 
;De exemplu: (((A B) C) (D E)) --> (A B C D E)

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
	


;;;;;;;;;;;in plus

( defun invers_poz(l in sf poz col)
	(cond 
		((null l) nil)
		(t (cond
				((= poz sf)
				 (append (list (car l)) (append  col (invers_poz (cdr l) in sf (+ poz 1) col))))
				((< poz in)
					 (append  (list(car l)) (invers_poz (cdr l) in sf (+ poz 1) col)) )
				((> poz sf)
					 (append (list(car l)) (invers_poz (cdr l) in sf (+ poz 1) col)))
				(T (invers_poz (cdr l) in sf (+ poz 1) (append (list(car l)) col)))
			)
		)
	)
)

(defun inversSecventa(l in sf)
	(invers_poz l in sf 1 nil))

(defun da_poz_aux(l e poz)
	(cond 
		((= (car l) e) poz)
		(t (da_poz_aux (cdr l) e (+ poz 1)))
	)
)

(defun da_poz(l e)
	(da_poz_aux l e 1))

(defun da_poz_inceput_aux(l k poz)
	(cond 
		((equal (car l) (car k)) poz)
		(t (da_poz_inceput_aux (cdr l) k (+ poz 1)))
	)
)

(defun da_poz_inceput(l k)
	(da_poz_inceput_aux l k 1))
