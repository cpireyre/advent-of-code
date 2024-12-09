(require '[criterium.core :as crit])
(require '[clojure.pprint :refer [pprint]])

(defn ->memory [compressed]
  (loop [[x & xs] compressed
         file true
         offset 0
         id 0
         files (sorted-map-by >)
         free []]
    (if (nil? x)
      [free files offset]
      (if file
        (recur xs false (+ offset x) (inc id)
               (conj files {offset [id x]}) free)
        (recur xs true  (+ offset x) id
               files (conj free [offset x]))))))


(defn find-hole [free this-pos this-big]
  (first (filter (fn [[pos size]] 
                   (and (< pos this-pos)
                        (<= this-big size)))
                free)))

(defn solve [file]
  (->> file
       slurp
       (re-seq #"\d")
       (map parse-long)
       ->memory
       defrag2))

(def s (solve "example.txt"))
