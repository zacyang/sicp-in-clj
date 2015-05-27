(ns sicp.ch3.bankacount_refactored)


(defn with-security [account pwd]
  (fn [entered-pwd]
    (if ( = pwd entered-pwd )
      account
      (fn [_] (fn [_] "Incorrect password"))
      ))
  )

(defn with-try-limit [account limit]
  (def tried (atom limit))

  (defn limit-check []
    (println "current count :"  @tried)
    (swap! tried dec)
    (>= (deref tried) 0)
    )

  (defn inner-fn 
    ([arg]
    (if (limit-check )
      (account arg)
      (fn [_]  "Limit exceeded, policy is informed!")))))
