;13. Se da un arbore de tipul (2). Sa se afiseze calea de la radacina pana la un
 ;nod x dat.
(defun arboreStang (l)
	( cond 
		( (null l) nil)
		( (= 1 (length l)) nil)
		(t (cadr l))
	)
)

(defun arboreDrept (l)
	( cond 
		( (null l) nil)
	 	( (= 1 (length l)) nil)
	 	(t (caddr l))
	)
)

(defun radacinaArbore (l)
	( car l)
)

(defun cale_aux (key l col)
	( cond 
		( ( null l) nil )
 		( (= key (radacinaArbore l))  (append col (list key)))
 		( t (append (cale_aux key (arboreDrept l) (append col (list(radacinaArbore l )))) (cale_aux key (arboreStang l) (append col (list(radacinaArbore l )))) ) )
 	)
)

(defun cale(x l)
	(cale_aux x l nil)
)