(ns sicp.ch3.bankacount)

(defn make-accumulator [original-val]
  (def tmp (atom original-val))
  (fn accumulator-gen [amount]
    (swap! tmp 
           #(+ amount % ))))
