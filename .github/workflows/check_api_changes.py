import os
import pygit2

def get_changed_files():
    repo_path = os.getcwd()
    repo = pygit2.Repository(repo_path)

    commit = repo[repo.head.target]
    parent = commit.parents[0]

    diff = repo.diff(parent, commit)
    return [patch.delta.new_file.path for patch in diff if patch.delta.new_file.path.endswith('.java')]


def read_annotations_from_file(file_path):
    with open(file_path, 'r') as f:
        return [line.strip() for line in f.readlines()]


def check_files_for_annotation(files, annotations):
    for file in files:
        with open(file, 'r') as f:
            lines = f.readlines()
            for line in lines():
                for annotation in annotations:
                    if annotation in line:
                        print(f'API annotation {annotation} found in {file}')


def main():
    files = get_changed_files()
    annotations = read_annotations_from_file('./annotations.txt')
    check_files_for_annotation(files, annotations)


if __name__ == '__main__':
    main()