(ns sicp.extra.debug)

(defn- readr 
  [prompt exit-code]
  (let [input (clojure.main/repl-read prompt exit-code)]
    (if (= input ::tl)
      exit-code
      input)))

(defmacro local-context
  []
  (let [symbols (keys &env)]
    (zipmap (map (fn [sym] `(quote ~sym)) symbols) symbols)))

(defmacro break
  []
  `(clojure.main/repl
    :prompt #(println "debug=> ")
    :read readr
    :eval (partial contextual-eval (local-context))))

(defn dvi 
  [n d]
  (break)
  (int (/ n d)))
