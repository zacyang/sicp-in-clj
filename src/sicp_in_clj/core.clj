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
