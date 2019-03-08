;1. Sa se construiasca o functie care intoarce adancimea unei liste.
(Defun adancime (l)
	(cond 
		((null (cdr l)) 0)
		((atom l) 0)
		( t (+ 1 (apply #'max (mapcar #'adancime (cdr l)))))
	)
)

;2. Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
 ;care apar, pe orice nivel, dar in aceeasi ordine. De exemplu
 ;(((A B) C) (D E)) --> (A B C D E)

(defun atomiT (l)
	(cond
		((numberp l) nil)
		((atom l) (list l))
		(t (apply #'append (mapcar #'atomiT l)))
	)
)

;3. Sa se construiasca o functie care verifica daca un atom e membru al
 ;unei liste nu neaparat liniara.
(defun nrAparitii ( l e)
	(cond
		((equal l e) 1)
		((atom l) 0)
		(t (apply #'+ (mapcar #'(lambda ( l)
									(nrAparitii l e)) l)))
	)
)

(defun apare (l e)
	(cond
		((> (nrAparitii l e) 0) t)
		( t nil)
	)
)

;4. Sa se construiasca o functie care intoarce suma atomilor numerici
 ;dintr-o lista, de la orice nivel.
(defun suma (l)
	(cond
		((numberp l) l)
		((atom l) 0)
		(t (apply #'+ (mapcar #'suma l)))
	)
)

;5. Definiti o functie care testeaza apartenenta unui nod intr-un arbore n-ar
 ;reprezentat sub forma (radacina lista_noduri_subarb1... lista_noduri_
 ;_subarbn)
 ;Ex: arborelele este (a (b (c)) (d) (e (f))) si nodul este 'b => adevarat

; 6. Sa se construiasca o functie care intoarce produsul atomilor numerici
 ;dintr-o lista, de la orice nivel.
(defun produs(l)
	(cond
		((numberp l)l)
		((atom l) 1)
		(t (apply #'* (mapcar #'produs l)))
))

;9. Definiti o functie care substituie un element E prin elementele
 ;unei liste L1 la toate nivelurile unei liste date L.

(Defun inloc (l k e)
	(cond
		((equal l e) k)
		((and (atom l) (not(equal l e))) (list l))
		(t (list (apply #'append (mapcar #'(lambda (l)
										(inloc l k e)) l))))
	)
)

;Definiti o functie care determina numarul nodurilor de pe nivelul k
 ;dintr-un arbore n-ar reprezentat sub forma (radacina lista_noduri_subarb1
 ;... lista_noduri_subarbn) Ex: arborelele este (a (b (c)) (d) (e (f))) si
;k=1 => 3 noduri 

(defun nrAK ( l k)
	(cond
		((= k 1) 1)
		( (not(= k 1)) 0)
		(t (apply #'+ (mapcar #' (lambda (l) (nrAK l (- k 1)) )l )))
))