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
;;; following func is from the book, included inorder to complete the question
(defn make-account [balance]
  (def stored-balance (atom balance))
  
  )

(defn make-withdraw [balance]
  (def stored-balance (atom balance))
  (fn [amount]
    (if (>= balance amount)
      (swap! stored-balance #(- % amount))
      "Insufficient funds"
      )))

;;; withdraw and deposit all in one
;; (defn make-account [balance]
;;   (def stored-balance (atom balance))
;;   (defmulti bank-opt (fn [opt] opt))
;;   (defmethod bank-opt :withdraw [_ amount]
;;     (fn [amount]
;;       (if (>= balance amount)
;;         (swap! stored-balance #(- % amount))
;;         "Insufficient funds"
;;       )))
;;   (defmethod bank-opt :deposit [_ amount]
;;     (fn [amount]
;;       (swap! stored-balance #(+ % amount))))
;;   (defmethod bank-opt :default [& _]
;;     "unsupported opration!")
;;     )

(defn make-account [balance]
  (def stored-balance (atom balance))
  (defn withdraw [amount]
    (if (>= balance amount)
      (swap! stored-balance #(- % amount))
      "Insufficient funds"
      ))
  (defn deposit [amount]
      (swap! stored-balance #(+ % amount)))
  (defn unsupport [_]
    "unsupported opration!")
  
  (fn [opration] 
    (cond (= :withdraw opration) withdraw
          (= :deposit opration) deposit
          :else (unsupport)
      )))
