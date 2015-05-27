(ns sicp.ch3.bankacount_refactored)


(defn with-security [account pwd]
  (fn [pwd]
    (if ((fn password-check [x] (= pwd x)) pwd)
      account
      (fn [_] "Incorrect password")
      ))
  )

(defn with-try-limit [account limit]
  (def ^:private tried (atom limit))

  (defn limit-check []
    (swap! tired-times dec)
    (if (<= @tried-times 0)
      "Limit exceeded, policy is informed!"
      ))

   (fn [limi]
    (if (limit-check )
      account
      (fn [_] "Incorrect password")
      ))
)
