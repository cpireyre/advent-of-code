(require '[clojure.pprint :refer [pprint]])

(defn spy [x]
  (do (pprint x)
      x))

(defn scan [{:keys [rule page]}]
  (letfn [(grok [text regx]
            (map #(Integer/parseInt %) (clojure.string/split text regx)))
          (rank [page]
            (into {} (map-indexed (fn [idx val] [val idx]) page)))]
    {:rule (map #(grok % #"\|") rule)
     :page (map #(rank (grok % #",")) page)}))

(defn parse [file]
  (->> (slurp file)
       clojure.string/split-lines
       (partition-by #(= % ""))
       (#(hash-map :rule (first %)
                   :page (last %)))
       (scan)))

(defn fits-rule? [rule page]
  (let [a (page (first rule))
        b (page (second rule))]
    (if-not (and a b)
      true
      (< a b))))

(defn fits? [rules page]
  (every? #(fits-rule? % page) rules))

(defn mid [page-map]
  (nth (sort-by page-map (keys page-map))
       (quot (count page-map) 2)))

(defn result [page]
  (apply + (map mid page)))

(defn part1 [file]
  (let [data (parse file)]
    (pprint (result (filter #(fits? (data :rule) %) (data :page))))))

; (part1 "puzzle-input.txt")
; (part1 "example.txt")

(defn breaks? [rule page]
  (when (not (fits-rule? rule page))
    rule))

(defn offenses [rule page]
  (remove nil? (map #(breaks? % page) rule)))

(defn swap [m [k1 k2]]
  (assoc m k1 (m k2) k2 (m k1)))

(defn fix [rule page]
  (if (breaks? rule page)
    (swap page [(first rule) (second rule)])
    page))

(defn fix-all [rules page]
  (reduce (fn [p rule] (fix rule p))
          page
          (offenses rules page)))

(def data (parse "example.txt"))
(def rules (data :rule))

(defn fix-and-sum [{:keys [rule page]}]
  (let [bad-pages (remove #(fits? rule %) page)
        fixed-pages (map #(fix-all rule %) bad-pages)]
    (map #(offenses rules %) bad-pages)))

(pprint (fix-and-sum data))

(defn make-graph [rules]
  (reduce (fn [g [a b]]
            (update g a (fnil conj #{}) b))
          {}
          rules))

(def graph (make-graph rules))
(pprint graph)
(vals graph)
(set (map second rules))
