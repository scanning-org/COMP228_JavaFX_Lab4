import os
import pygit2

def get_changed_files():
    repo_path = os.getcwd()
    repo = pygit2.Repository(repo_path)

    commit = repo[repo.head.target]
    parent = commit.parents[0]

    diff = repo.diff(parent, commit)
    return [patch.delta.new_file.path for patch in diff if patch.delta.new_file.path.endswith('.java')]


def check_files_for_annotation(files):
    annotations = ['@RestController', '@RequestMapping']
    for file in files:
        with open(file, 'r') as f:
            lines = f.readlines()
            for line in lines:
                for annotation in annotations:
                    if annotation in line:
                        print(f'API annotation {annotation} foun in {file}')


def main():
    files = get_changed_files()
    check_files_for_annotation(files)


if __name__ == '__main__':
    main()