(ns sicp-in-clj.core) 

(defn last-pair
  "return the last item in the list, I will be limited to use first and last only"
  [col]
  
  (if (>= 1 (count col))
    (first col)
    (last-pair (rest col))
    )
  )

(defn reverse-list
  "reverse the list"
  [col]
  (if (<= 0 (count col))
    (reduce conj () col )
    col
    )
  )

(defn element-of-set?
  [element col]
  (cond (nil? col) false
        (empty? col) false
        (= element (first col)) true
        :else (element-of-set? element (rest col))
        )
  )

(defn adjoin-set
  [element col]
  (if (element-of-set? element col)
    col
    (cons element col))
  )

(defn interaction-set
  [set1 set2]
  (cond (or (empty? set1) (empty? set2)) '()
        (element-of-set? (first set1) set2)
        (cons (first set1)
              (interaction-set (rest set1) set2)) 
        :else (interaction-set (rest set1) set2)
        ))

(defn union-set
  [set1 set2]
  (cond (empty? set1) set2
        (empty? set2) set1
        (not (element-of-set? (first set1) set2)) (cons (first set1) (union-set (rest set1) set2))
        (element-of-set? (first set1) set2) (union-set (rest set1) set2)
        )
  ) 


;; polor and retangular
(defn square
  [x]
  (* x x))
;; retangular representation of fushu... LoL

;; (defn real-part
;;   [z]
;;   first z)

;; (defn imag-part
;;   [z]
;;   (rest z))

;; (defn magnitude
;;   [z]
;;   (Math/sqrt (+ (square (real-part z))
;;                 (square (imag-part z)))))

;; (defn angle
;;   [z]
;;   z)

(defn make-from-real-imag
  [x y]
  (cons x y))

(defn make-from-mag-ang
  [r a]
  (cons (* r (Math/cos a)) (* r (Math/sin a))))

;;polor
(defn real-part
  [z]
  
  )







