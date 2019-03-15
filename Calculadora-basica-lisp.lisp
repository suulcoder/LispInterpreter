;;;;Roberto Figueroa, Michele Benvenuto, Saul Contreras
;;;;Calculadora en Lisp

(atom nil) ;valor :  verdadero 
(list 1 2 3 4) ; esta es una lista con valores 1, 2, 3, 4       


(format t "Bienvenido a una colaculadora basica en LISP ~%" )
(princ "Ingrese el primer dato: ")
;definicion de variables
(defvar dato1 0)
(defvar dato2 0)
(defvar resultado 0)
;asignacion de datos
(setq dato1 (read))
(princ "Ingrese el segundo dato: ")
(setq dato2 (read))
(defvar opcion)
(format t "1.-Sumar~%2.-Restar~%3.Dividir~%4.Multiplicar~%" )
(princ "Ingrese una opcion :  ")
(setq opcion (read))
;las cond nos permiten verificar si el usuario elijio determinada instruccion
;si elijio la opc. 1: va a sumar los datos
(cond ((eql opcion 1) 

	(setq resultado (+ dato1 dato2))
	(format t "~%El resultado de la suma es ~d" resultado))
	)
;si elijio la opc. 2: va a restar los datos
(cond ((eql opcion 2) 

	(setq resultado (- dato1 dato2))
	(format t "~%El resultado de la resta es ~d" resultado))
	)

;si elijio la opc. 3: va a dividir los datos
(cond ((eql opcion 3) 

	(setq resultado (/ dato1 dato2))
	(format t "~%El resultado de la division es ~d" resultado))
	)
;si elijio la opc. 4: va a multiplicar los datos
(cond ((eql opcion 4) 

	(setq resultado (* dato1 dato2))
	(format t "~%El resultado de la multiplicacion es ~d" resultado))
	)