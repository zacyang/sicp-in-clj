(ns sicp.ch2.core)

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
    (reduce conj () col)
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

(defn lz-step [s]
  (lazy-seq
   (if (seq s)
     [(first s) (lz-step (rest s))]
     [])))

;;; delay version tri
(defn triangles [n]
  (/ (* n (+ n 1))
     2))
(defn inf-triangles [n]
  {:head (triangles n)
   :tail (delay (inf-triangles (inc n)))})

(defn head [t] (:head t))

(defn tail [t] (force (:tail t)))

(defn taker [n l]
  (loop [t n, src l, ret []]
    (if (zero? t)
      ret
      (recur (dec t) (tail src) (conj ret (head src))))))

(defn nthr [l n]
  (if (zero? n)
    (head l)
    (recur (tail l) (dec n))))

;;; Joy , exmaple of quick sort , using lazy 
(defn nom [n]
  (take n (repeatedly #(rand-int n))))


(defn- sort-parts [work]
  (lazy-seq
   (loop [[part & parts] work]
     (if-let [[pivot & xs] (seq part)]
       (let [smaller? #(< % pivot)]
         (recur (list* (filter smaller? xs)
                       pivot
                       (remove smaller? xs)
                       parts)))
       (when-let [[x & left-parts] parts]
         (cons x (sort-parts left-parts)))))))

(defn quick-lazy-sort [xs]
  (sort-parts (list xs)))


;;; joy chap7

(defn keys-apply 
  [f ks m]
  (let [only (select-keys m ks)]
    (zipmap (keys only) (map f (vals only))
            )))
;;; default
(defn slope
  [& {:keys [p1 p2] :or {p1 [0 0] , p2 [1 1]}}]
  ""
  (float (/ (- (p2 1) (p1 1))
            (- (p2 0) (p1 0)))))

;;; :pre and :post

(defn plus-something
  [a b]
  {:pre [(not zero? a)]}
  a
)
