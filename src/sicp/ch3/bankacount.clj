(ns sicp.ch3.bankacount)

(defn make-accumulator [original-val]
  (def tmp (atom original-val))
  (fn accumulator-gen [amount]
    (swap! tmp 
           #(+ amount % ))))


(defn square [x]
  (* x x))

(defn make-monitored [watched-fn]
  (def counter (atom 0))
  (defn- increase-counter-and-invok-watched-fn [watched-fn args]
        (swap! counter inc)
        (watched-fn args))
  (fn [args]
    (if (and  (keyword? args) (= args :how-many-calls?))
      (deref counter)
      (increase-counter-and-invok-watched-fn watched-fn args)
      )))
