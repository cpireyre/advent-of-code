; (def infile "example.txt")
(def infile "puzzle-input.txt")

(defn parse [xs]
  (map #(Integer/parseInt %) xs))

(defn diff-pairs [xs]
  (map - (rest xs) xs))

(defn monotonic? [xs]
  (or (every? pos? xs) (every? neg? xs)))

(defn gradual? [xs]
  (every? #(<= 1 (Math/abs %) 3) xs))

(defn safe? [xs]
  (let [diffs (diff-pairs xs)]
    (and (monotonic? diffs) (gradual? diffs))))

; part one
(println "Part one:"
  (->> (slurp infile)
       (clojure.string/split-lines)
       (map #(clojure.string/split % #"\s+"))
       (map parse)
       (map safe?)
       (remove false?)
       (count)))

; part two
(defn dampened [xs]
  (for [i (range (count xs))]
    (vec (concat (take i xs) (drop (inc i) xs)))))

(defn dampened-safe? [xs]
  (or (safe? xs)
      (some true? (map safe? (dampened xs)))))

(println "Part two:"
  (->> (slurp infile)
       (clojure.string/split-lines)
       (map #(clojure.string/split % #"\s+"))
       (map parse)
       (map dampened-safe?)
       (remove nil?)
       (count)))
