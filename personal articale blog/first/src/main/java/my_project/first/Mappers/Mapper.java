package my_project.first.Mappers;

public interface Mapper<A,B> {
    public A mapTo(B b);
    public B mapFrom(A a);
}
