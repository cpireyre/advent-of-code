(require '[clojure.pprint :refer [pprint]])

(defn slurp-lines [file]
  (clojure.string/split-lines (slurp file)))

(def file "example.txt")

(defn parse [file]
  (let [lines (slurp-lines file)
        [rules _ updates] (partition-by empty? lines)]
    [(set (mapv #(clojure.string/split % #"\|") rules))
     (mapv #(clojure.string/split % #",") updates)]))

(defn update->rules [update-v]
  (for [i (range 0 (dec (count update-v)))
        j (range (inc i) (count update-v))]
    [(get update-v i) (get update-v j)]))

(defn disordered? [rules-set update-rules]
  (let [anti-rule (fn [[a b]] [b a])]
    (some #(contains? rules-set (anti-rule %)) update-rules)))

(defn middle-n [update-v]
  (Integer/parseInt (get update-v (int (/ (count update-v) 2)))))

(defn ->compare [rules-set]
  (fn [a b]
    (cond
      (contains? rules-set [a b]) -1
      (contains? rules-set [b a]) 1
      :else                       0)))

(defn solve [file]
  (let [[rules-set updates] (parse file)]
    (->> updates
         (keep (fn [update-v]
                 (when (disordered? rules-set (update->rules update-v))
                   update-v)))
         (mapv #(vec (sort-by identity (->compare rules-set) %)))
         (map middle-n)
         (apply +))))

(solve "puzzle-input.txt")
