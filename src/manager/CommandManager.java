package manager;

import commands.Executable;

public class CommandManager {
    private Executable info;
    private Executable help;
    private Executable show;
    private Executable add;
    private Executable update;
    private Executable remove_by_id;
    private Executable clear;
    private Executable save;
    private Executable execute_script;
    private Executable exit;
    private Executable add_if_max;
    private Executable add_if_min;
    private Executable remove_greater;
    private Executable remove_all_by_car;
    private Executable max_by_creation_date;
    private Executable filter_greater_than_soundtrack_name;

    public CommandManager(Executable info,
                         Executable help,
                         Executable show,
                         Executable add,
                         Executable update,
                         Executable remove_by_id,
                         Executable clear,
                         Executable save,
                         Executable execute_script,
                         Executable exit,
                         Executable add_if_max,
                         Executable add_if_min,
                         Executable remove_greater,
                         Executable remove_all_by_car,
                         Executable max_by_creation_date,
                         Executable filter_greater_than_soundtrack_name) {
        this.info = info;
        this.help = help;
        this.show = show;
        this.add = add;
        this.update = update;
        this.remove_by_id = remove_by_id;
        this.clear = clear;
        this.save = save;
        this.execute_script = execute_script;
        this.exit = exit;
        this.add_if_max = add_if_max;
        this.add_if_min = add_if_min;
        this.remove_greater = remove_greater;
        this.remove_all_by_car = remove_all_by_car;
        this.max_by_creation_date = max_by_creation_date;
        this.filter_greater_than_soundtrack_name = filter_greater_than_soundtrack_name;
    }

    public boolean info(String str) {
        return info.execute(str);
    }

    public boolean help(String str) {
        return help.execute(str);
    }

    public boolean show(String str) {
        return show.execute(str);
    }

    public boolean add(String str) {
        return add.execute(str);
    }

    public boolean update(String str) {
        return update.execute(str);
    }

    public boolean remove_by_id(String str) {
        return remove_by_id.execute(str);
    }

    public boolean clear(String str) {
        return clear.execute(str);
    }

    public boolean save(String str) {
        return save.execute(str);
    }

    public boolean execute_script(String str) {
        return execute_script.execute(str);
    }
    public boolean exit(String str) {
        return exit.execute(str);
    }
    public boolean add_if_max(String str) {
        return add_if_max.execute(str);
    }
    public boolean add_if_min(String str) {
        return add_if_min.execute(str);
    }
    public boolean remove_greater(String str) {
        return remove_greater.execute(str);
    }
    public boolean remove_all_by_car(String str) {
        return remove_all_by_car.execute(str);
    }
    public boolean max_by_creation_date(String str) {
        return max_by_creation_date.execute(str);
    }
    public boolean filter_greater_than_soundtrack_name(String str) {
        return filter_greater_than_soundtrack_name.execute(str);
    }
}
