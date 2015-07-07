(ns sicp.ch2.list)

;;; ops to a list
;;;  1. if n = 0, list-ref should return car
;;;  2. else, list-ref returns cdr's (n-1)th element

(defn list-ref [items n]
  (if (zero? n)
    (first items)
    (list-ref (rest items) (dec n)))
)

(defn list-length [items]
  (if (empty? items)
    0
    (+ 1 (list-length (rest items)))))
;;; this is the basic form of recur, if some cond matches ENDs, else continue recur, NOTICE *MUST* recur at the tail

;;; iter approach
(defn list-length-it [items]
  (defn len-iter [c count]
    (if  (empty? c)
      count
   (len-iter (rest c) (inc count))))

  (len-iter items 0))

;;; if items-1 is empty, return items-2
;;; else, append items-2 to (rest items-1) , and cons (first items-1)
;;; and the sum
(defn list-append [items-1 items-2]
  items-2
  (cons (first items-1) (list-append  (rest items-1) items-2))
)

;;; 2.17 defn last-pair, this fn should return the (if not nil) col's last element
(defn last-pair [col]
  (if (empty? col)
    (throw (IllegalArgumentException. "SHOULD NOT BE NIL")) 
    (if (empty? (rest col))
      (first col)
      (last-pair (rest col))))) 

;;; 2.18 defn reverse
(defn col-reverse [col]
  (if (empty? col)
    nil
    (reduce conj '()  col)
))

;;; a complex way....a "normal" way...
(defn col-reverse-2 [lst] 
  (defn iter [remained-items result]
    (if (empty? remained-items)
      result
      (iter (rest remained-items)
            (cons (first remained-items) result))))
    (iter lst '()))

