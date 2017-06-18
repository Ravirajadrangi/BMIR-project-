import os
import uuid

def replace():
    replacements = {'Unsigned': ''}

    with open(inpath) as infile, open(outpath, 'w') as outfile:
        for line in infile:
            for src, target in replacements.iteritems():
                line = line.replace(src, target)
            outfile.write(line)

print(uuid.uuid4())
