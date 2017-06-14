import xml.etree.ElementTree as ET
import re
tree = ET.parse('smokers_surrogate_test_all_groundtruth_version2.xml')
#this filename can be changed to accommodate different smoker files
root = tree.getroot()

#file = open("smokers_annotated_train_aggregate", "w")
for element in root.findall('RECORD'):
    s= str(element.attrib)

    name = re.findall('\d+', s)[0]
    text = element.find('TEXT').text
    if "UNK" in str(element.find("SMOKING").attrib['STATUS']):

        #file = open(name, "w")
        #file.write(str(text))
