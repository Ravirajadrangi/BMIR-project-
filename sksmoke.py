import xml.etree.ElementTree as ET
import re
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.naive_bayes import MultinomialNB
from sklearn.pipeline import Pipeline
import numpy as np

data = []
target = []
categories = ["current", "past", "non", "unknown"]


def parse(filename, status):
    tree = ET.parse(filename)
    #this filename can be changed to accommodate different smoker files
    root = tree.getroot()

    for element in root.findall('RECORD'):
        text = str(element.find('TEXT').text)[50:]
        data.append(text)
        if status:
            s= str(element.find("SMOKING").attrib['STATUS'])
            if "UNK" in s:
                target.append(4)
            elif "NON" in s:
                target.append(3)
            elif "PAST" in s:
                target.append(2)
            else:
                target.append(1)

parse('smokers_surrogate_train_all_version2.xml', True)
count_vect = CountVectorizer()
X_train_counts = count_vect.fit_transform(data)
tf_transformer = TfidfTransformer(use_idf=False).fit(X_train_counts)
X_train_tf = tf_transformer.transform(X_train_counts)
tfidf_transformer = TfidfTransformer()
X_train_tfidf = tfidf_transformer.fit_transform(X_train_counts)
clf = MultinomialNB().fit(X_train_tfidf, target)

parse('smokers_surrogate_test_all_groundtruth_version2.xml', True)
X_new_counts = count_vect.transform(data)
X_new_tfidf = tfidf_transformer.transform(X_new_counts)
predicted = clf.predict(X_new_tfidf)
print(predicted)

text_clf = Pipeline([('vect', CountVectorizer()),
                     ('tfidf', TfidfTransformer()),
                     ('clf', MultinomialNB()),
])

print(np.mean(predicted == target))

# parse('smokers_surrogate_test_all_groundtruth_version2.xml', True)
# predicted = text_clf.predict(data)
# np.mean(predicted == target)

