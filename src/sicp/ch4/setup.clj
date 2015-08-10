(ns sicp.ch4.setup
  (:require [sicp.ch4.core :as core]  ))


(defn setup-environment
  []
  (let [initial-env (core/extend-enviroment core/primitive-procedure-names
                                            core/primitive-procedure-objects
                                            core/the-empty-environment)]
    (->> initial-env
         (core/define-variable! 'true true)
         (core/define-variable! 'false false))

))

(def the-global-environment (setup-environment))


