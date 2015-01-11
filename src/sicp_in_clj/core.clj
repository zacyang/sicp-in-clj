(ns sicp-in-clj.core) 

(defn last-pair
  "return the last item in the list, I will be limited to use first and last only"
  [col]
  
  (if (>= 1 (count col))
    (first col)
    (last-pair (rest col))
    )
  )  

